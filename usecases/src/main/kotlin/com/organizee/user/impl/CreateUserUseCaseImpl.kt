package com.organizee.user.impl

import com.organizee.boundary.db.services.UserService
import com.organizee.user.CreateUserUseCase
import com.organizee.user.User
import com.organizee.user.commands.NewUserCommand

class CreateUserUseCaseImpl(private val userService: UserService) : CreateUserUseCase {
    override fun execute(input: NewUserCommand): User {

        val userByEmail = userService.findByEmail(input.email)
        if (userByEmail != null)
            throw RuntimeException("Email already taken")

        val userByUsername = userService.findByUsername(input.username)
        if (userByUsername != null)
            throw RuntimeException("Username already taken")


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