package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.domain.user.User
import com.organizee.usecases.user.CreateUserUseCase
import com.organizee.usecases.user.commands.NewUserCommand
import org.springframework.stereotype.Service

@Service
class CreateUserUseCaseImpl(
    private val userService: UserService
) : CreateUserUseCase {
    override fun execute(input: NewUserCommand): User {
        val user = User.createNormalUser(
            id = input.userId,
            name = input.name,
            surname = input.surname,
            username = input.username,
            description = input.description
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

