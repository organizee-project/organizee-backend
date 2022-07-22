package com.organizee.user.impl

import com.organizee.boundary.db.services.UserService
import com.organizee.user.CreateUserUseCase
import com.organizee.user.User
import com.organizee.user.commands.NewUserCommand

class CreateUserUseCaseImpl(private val userService: UserService) : CreateUserUseCase {
    override fun execute(input: NewUserCommand): User {

        val user = User.createNormalUser(
            email = input.email,
            name = input.name,
            surname = input.surname,
            username = input.username,
            password = input.password
        )

        return userService.create(user)
    }
}