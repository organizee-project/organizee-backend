package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.domain.user.User
import com.organizee.usecases.user.CreateUserUseCase
import com.organizee.usecases.user.commands.NewUserCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateUserUseCaseImpl(
    private val userService: UserService
) : CreateUserUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: NewUserCommand): User {

        logger.info("Adding new user | input: $input")

        val user = User.createNormalUser(
            id = input.userId,
            name = input.name,
            surname = input.surname,
            username = input.username,
            description = input.description,
            imgUrl = input.imgUrl
        )

        validate(user)

        return userService.create(user)
    }

    private fun validate(user: User) {
        if (userService.userExists(user.username))
            throw ErrorCodes.USERNAME_ALREADY_EXISTS_EXCEPTION(listOf(user.username))

        if (userService.findById(user.id) != null)
            throw ErrorCodes.USER_ALREADY_EXISTS_EXCEPTION()
    }
}

