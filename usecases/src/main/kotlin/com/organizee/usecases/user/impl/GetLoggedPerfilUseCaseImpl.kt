package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.usecases.user.GetLoggedPerfilUsecase
import com.organizee.usecases.user.responses.PerfilUseCaseResponse
import com.organizee.usecases.user.responses.UserPerfilUseCaseResponse
import org.springframework.stereotype.Service

@Service
class GetLoggedPerfilUseCaseImpl(
    private val userService: UserService,
) : GetLoggedPerfilUsecase {
    override fun execute(userId: String): PerfilUseCaseResponse {

        val user = userService.findByUserIdOrThrow(userId)

        return PerfilUseCaseResponse(
            user = UserPerfilUseCaseResponse(
                imgUrl = user.imgUrl,
                fullName = user.getFullName(),
                username = user.username,
                description = user.description
            )
        )

    }


}