package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.user.User
import com.organizee.usecases.user.UpdateUserUseCase
import com.organizee.usecases.user.commands.UpdateUserCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UpdateUserUseCaseImpl(
    private val userService: UserService
) : UpdateUserUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: UpdateUserCommand): User {

        logger.info("Updating user | input: $input")

        var user = userService.findByUserIdOrThrow(input.userId)

        user = user.update(input.name, input.surname, input.description, input.imgUrl)

        return userService.update(user)
    }
}

