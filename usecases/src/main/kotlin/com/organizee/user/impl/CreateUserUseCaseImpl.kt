package com.organizee.user.impl

import com.organizee.boundary.db.services.UserService
import com.organizee.exceptions.BussinessException
import com.organizee.exceptions.ExceptionMessage
import com.organizee.user.CreateUserUseCase
import com.organizee.user.User
import com.organizee.user.commands.NewUserCommand

class CreateUserUseCaseImpl(private val userService: UserService) : CreateUserUseCase {
    override fun execute(input: NewUserCommand): User {

        val userByEmail = userService.findByEmail(input.email)
        if (userByEmail != null)
            throw BussinessException(ExceptionMessage.EMAIL_ALREADY_IN_USE)

        val userByUsername = userService.findByUsername(input.username)
        if (userByUsername != null)
            throw BussinessException(ExceptionMessage.USERNAME_ALREADY_IN_USE)


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