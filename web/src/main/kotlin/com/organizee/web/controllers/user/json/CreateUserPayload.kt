package com.organizee.web.controllers.user.json

import com.organizee.usecases.user.commands.NewUserCommand

data class CreateUserPayload(
    val name: String,
    val surname: String,
    val username: String,
    val description: String = ""
) {
    fun toUseCaseInput(userId: String): NewUserCommand {
        return NewUserCommand(
            name = name,
            surname = surname,
            username = username,
            description = description,
            userId = userId
        )
    }
}