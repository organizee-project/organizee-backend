package com.organizee.web.controllers.user

import com.organizee.domain.Page
import com.organizee.usecases.guide.GetUserGuidesUseCase
import com.organizee.usecases.guide.commands.GetUserGuidesCommand
import com.organizee.usecases.user.CreateUserUseCase
import com.organizee.usecases.user.GetLoggedPerfilUsecase
import com.organizee.usecases.user.GetPublicPerfilUsecase
import com.organizee.usecases.user.UpdateUserUseCase
import com.organizee.usecases.user.commands.GetPerfilCommand
import com.organizee.usecases.user.responses.PerfilUseCaseResponse
import com.organizee.web.controllers.guide.json.responses.GuideResponse
import com.organizee.web.controllers.user.json.CreateUserPayload
import com.organizee.web.controllers.user.json.UpdateUserPayload
import com.organizee.web.controllers.user.json.UserResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.security.Principal


@RestController
@RequestMapping(value = ["v1/users"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getPublicPerfilUsecase: GetPublicPerfilUsecase,
    private val getLoggedUserPerfilUsecase: GetLoggedPerfilUsecase,
    private val getUserGuidesUseCase: GetUserGuidesUseCase
) {
    @PostMapping
    fun create(@RequestBody input: CreateUserPayload, principal: Principal): UserResponse =
        UserResponse.fromEntity(createUserUseCase.execute(input.toUseCaseInput(principal.name)))


    @PatchMapping
    fun update(@RequestBody input: UpdateUserPayload, principal: Principal): UserResponse {
        val user = updateUserUseCase.execute(input.toUseCaseInput(principal.name))
        return UserResponse.fromEntity(user)
    }


    @GetMapping("/logged")
    fun getLoggedPerfil(
        principal: Principal
    ): PerfilResponse {
        val perfil = getLoggedUserPerfilUsecase.execute(principal.name)

        return PerfilResponse.from(perfil)
    }

    @GetMapping("{username}")
    fun getPerfil(
        @PathVariable("username") username: String,
        principal: Principal?
    ): PerfilResponse {
        val perfil = getPublicPerfilUsecase.execute(GetPerfilCommand(principal?.name, username))

        return PerfilResponse.from(perfil)
    }


    @GetMapping("{username}/guides")
    fun getPerfilGuides(
        @PathVariable("username") username: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int,
        principal: Principal?
    ): Page<GuideResponse> {

        val guides = getUserGuidesUseCase.execute(
            GetUserGuidesCommand.create(
                principal?.name,
                username,
                page,
                size
            )
        )

        return Page(
            items = guides.items.map {
                GuideResponse.fromEntity(it)
            },
            totalPages = guides.totalPages,
            count = guides.count,
            currentPage = guides.currentPage,
            nextPage = guides.nextPage
        )
    }
}


data class PerfilResponse(
    val user: UserPerfilResponse
) {
    companion object {
        fun from(perfil: PerfilUseCaseResponse) = PerfilResponse(
            user = UserPerfilResponse(
                imgUrl = perfil.user.imgUrl,
                fullName = perfil.user.fullName,
                username = perfil.user.username,
                description = perfil.user.description,
                isFollowed = perfil.user.isFollowed,
                followers = perfil.user.followers.map {
                    UserPerfilResponse(
                        imgUrl = it.imgUrl,
                        fullName = it.fullName,
                        username = it.username,
                        description = it.description
                    )
                },
                following = perfil.user.following.map {
                    UserPerfilResponse(
                        imgUrl = it.imgUrl,
                        fullName = it.fullName,
                        username = it.username,
                        description = it.description
                    )
                },
            )
        )

    }
}


data class UserPerfilResponse(
    val imgUrl: String,
    val fullName: String,
    val username: String,
    val description: String,
    val followers: List<UserPerfilResponse> = emptyList(),
    val following: List<UserPerfilResponse> = emptyList(),
    val isFollowed: Boolean? = null
)


