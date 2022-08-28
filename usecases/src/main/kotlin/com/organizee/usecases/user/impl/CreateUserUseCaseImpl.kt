package com.organizee.usecases.user.impl

import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.exceptions.BussinessException
import com.organizee.domain.exceptions.ExceptionMessage
import com.organizee.domain.user.User
import com.organizee.usecases.user.CreateUserUseCase
import com.organizee.usecases.user.commands.NewUserCommand
import org.springframework.stereotype.Service

@Service
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