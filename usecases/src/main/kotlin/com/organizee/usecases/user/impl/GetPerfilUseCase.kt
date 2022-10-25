package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.usecases.user.GetPublicPerfilUsecase
import com.organizee.usecases.user.responses.PerfilUseCaseResponse
import com.organizee.usecases.user.responses.UserPerfilUseCaseResponse
import org.springframework.stereotype.Service

@Service
class GetPerfilUseCase(
    private val userService: UserService,
) : GetPublicPerfilUsecase {
    override fun execute(username: String): PerfilUseCaseResponse {

        val user = userService.findByUsernameOrThrow(username)

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
                }
            )
        )

    }


}