package com.organizee.api.controllers.user.json

import com.organizee.user.commands.NewUserCommand

data class CreateUserPayload(
    val name: String,
    val surname: String,
    val username: String,
    val email: String,
    val password: String,
    val validatePassword: String
) {
    fun toUseCaseInput(): NewUserCommand {

        if (!validatePassword())
            throw RuntimeException("Password does not match")

        return NewUserCommand(
            email = email,
            name = name,
            surname = surname,
            username = username,
            password = password,
        )
    }

    private fun validatePassword() = password == validatePassword
}