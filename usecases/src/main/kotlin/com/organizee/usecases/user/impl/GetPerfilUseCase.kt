package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.UserService
import com.organizee.usecases.user.GetPublicPerfilUsecase
import com.organizee.usecases.user.responses.PerfilUseCaseResponse
import com.organizee.usecases.user.responses.UserPerfilUseCaseResponse
import org.springframework.stereotype.Service

@Service
class GetPerfilUseCase(
    private val userService: UserService,
    private val guideService: GuideService
) : GetPublicPerfilUsecase {
    override fun execute(username: String): PerfilUseCaseResponse {

        val user = userService.findByUsernameOrThrow(username)

        val guides = guideService.getAllPublicByUserId(user.id!!)

        return PerfilUseCaseResponse(
            user = UserPerfilUseCaseResponse(
                imgUrl = "",
                fullName = user.getFullName(),
                username = user.username,
                description = ""
            ), guides = guides, likes = listOf(), activities = listOf()

        )

    }


}