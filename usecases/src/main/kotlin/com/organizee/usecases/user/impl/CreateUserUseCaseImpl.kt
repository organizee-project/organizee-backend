package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
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

        val found = userService.userExists(user.username) || userService.findById(user.id) != null

        if (found) {
            throw Exception("User already exists")
        }

        return userService.create(user)
    }
}