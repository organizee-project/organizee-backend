package com.organizee.web.controllers.user

import com.organizee.domain.Page
import com.organizee.domain.user.Activity
import com.organizee.usecases.guide.GetUserGuidesUseCase
import com.organizee.usecases.guide.commands.GetUserGuidesCommand
import com.organizee.usecases.user.CreateUserUseCase
import com.organizee.usecases.user.GetLoggedPerfilUsecase
import com.organizee.usecases.user.GetPublicPerfilUsecase
import com.organizee.usecases.user.responses.PerfilUseCaseResponse
import com.organizee.web.controllers.guide.json.responses.GuideResponse
import com.organizee.web.controllers.user.json.CreateUserPayload
import com.organizee.web.controllers.user.json.UserResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.time.LocalDateTime
import java.util.*


@RestController
@RequestMapping(value = ["v1/users"], produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val getPublicPerfilUsecase: GetPublicPerfilUsecase,
    private val getLoggedUserPerfilUsecase: GetLoggedPerfilUsecase,
    private val getUserGuidesUseCase: GetUserGuidesUseCase
) {
    @PostMapping
    fun create(@RequestBody input: CreateUserPayload, principal: Principal): UserResponse =
        UserResponse.fromEntity(createUserUseCase.execute(input.toUseCaseInput(principal.name)))

    @GetMapping("/logged")
    fun getLoggedPerfil(
        principal: Principal
    ): PerfilResponse {
        val perfil = getLoggedUserPerfilUsecase.execute(principal.name)

        return PerfilResponse.from(perfil)
    }

    @GetMapping("{username}")
    fun getPerfil(
        @PathVariable("username") username: String
    ): PerfilResponse {
        val perfil = getPublicPerfilUsecase.execute(username)

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
            itens = guides.itens.map {
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
                description = perfil.user.description
            )
        )

    }
}

data class UserPerfilResponse(
    val imgUrl: String,
    val fullName: String,
    val username: String,
    val description: String
)

data class ActivityResponse(
    val id: UUID,
    val date: LocalDateTime,
    val type: String,
    val guideReference: GuideResponse?
) {
    companion object {
        fun fromEntity(activity: Activity) =
            ActivityResponse(
                activity.id,
                activity.date,
                activity.type,
                GuideResponse.fromEntity(activity.guideReference)
            )
    }
}


