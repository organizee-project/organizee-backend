package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.UserService
import com.organizee.usecases.user.GetPrivatePerfilUsecase
import com.organizee.usecases.user.commands.GetPrivatePerfilCommand
import com.organizee.usecases.user.responses.PerfilUseCaseResponse
import com.organizee.usecases.user.responses.UserPerfilUseCaseResponse
import org.springframework.stereotype.Service

@Service
class GetPrivatePerfilUsecase(
    private val userService: UserService,
    private val guideService: GuideService
) : GetPrivatePerfilUsecase {
    override fun execute(input: GetPrivatePerfilCommand): PerfilUseCaseResponse {

        val user = userService.findByUserIdOrThrow(input.userId)

        val guides = guideService.getAllByUserId(input.userId)

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