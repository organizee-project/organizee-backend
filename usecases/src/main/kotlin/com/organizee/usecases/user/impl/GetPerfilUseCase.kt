package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.usecases.user.GetPublicPerfilUsecase
import com.organizee.usecases.user.commands.GetPerfilCommand
import com.organizee.usecases.user.responses.PerfilUseCaseResponse
import com.organizee.usecases.user.responses.UserPerfilUseCaseResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class
GetPerfilUseCase(
    private val userService: UserService,
) : GetPublicPerfilUsecase {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: GetPerfilCommand): PerfilUseCaseResponse {
        logger.info("Getting user perfil | input=$input")

        val user = userService.findByUsernameOrThrow(input.userName)

        val isFollowed = input.userId?.let {
            userService.userFollows(it, user.username)
        } ?: false

        return PerfilUseCaseResponse(
            user = UserPerfilUseCaseResponse(
                imgUrl = user.imgUrl,
                fullName = user.getFullName(),
                username = user.username,
                description = user.description,
                following = user.following.map {
                    UserPerfilUseCaseResponse(
                        imgUrl = it.imgUrl,
                        fullName = it.getFullName(),
                        username = it.username,
                        description = it.description
                    )
                },
                followers = user.followers.map {
                    UserPerfilUseCaseResponse(
                        imgUrl = it.imgUrl,
                        fullName = it.getFullName(),
                        username = it.username,
                        description = it.description
                    )
                },
                isFollowed = isFollowed
            )
        )

    }
}