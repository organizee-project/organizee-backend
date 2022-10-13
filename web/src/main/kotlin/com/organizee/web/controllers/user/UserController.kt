package com.organizee.web.controllers.user

import com.organizee.domain.user.Activity
import com.organizee.usecases.user.CreateUserUseCase
import com.organizee.usecases.user.GetPrivatePerfilUsecase
import com.organizee.usecases.user.GetPublicPerfilUsecase
import com.organizee.usecases.user.commands.GetPrivatePerfilCommand
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
    private val getPrivatePerfilUsecase: GetPrivatePerfilUsecase,

    ) {
    @PostMapping
    fun create(@RequestBody input: CreateUserPayload, principal: Principal): UserResponse =
        UserResponse.fromEntity(createUserUseCase.execute(input.toUseCaseInput(principal.name)))

    @GetMapping("{username}/perfil")
    fun getPublicPerfil(
        @PathVariable("username") username: String
    ): PerfilResponse {
        val perfil = getPublicPerfilUsecase.execute(username)

        return PerfilResponse.from(perfil)
    }

    @GetMapping("/perfil")
    fun getPrivatePerfil(
        user: Principal
    ): PerfilResponse {
        val perfil = getPrivatePerfilUsecase.execute(GetPrivatePerfilCommand(user.name))

        return PerfilResponse.from(perfil)
    }
}


data class PerfilResponse(
    val user: UserPerfilResponse,
    val guides: List<GuideResponse> = emptyList(),
    val likes: List<GuideResponse> = emptyList(),
    val activities: List<ActivityResponse> = emptyList(),
) {
    companion object {
        fun from(perfil: PerfilUseCaseResponse) = PerfilResponse(
            user = UserPerfilResponse(
                imgUrl = perfil.user.imgUrl,
                fullName = perfil.user.fullName,
                username = perfil.user.username,
                description = perfil.user.description
            ), guides = perfil.guides.map {
                GuideResponse.fromEntity(it)
            }, likes = perfil.likes.map {
                GuideResponse.fromEntity(it)
            }, activities = perfil.activities.map { ActivityResponse.fromEntity(it) }
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


