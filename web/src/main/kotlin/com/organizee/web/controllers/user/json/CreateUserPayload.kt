package com.organizee.web.controllers.user.json

import com.organizee.usecases.user.commands.NewUserCommand

data class CreateUserPayload(
    val name: String,
    val surname: String,
    val username: String,
    val description: String? = null,
    val imgUrl: String? = null
) {
    fun toUseCaseInput(userId: String): NewUserCommand {
        return NewUserCommand(
            name = name,
            surname = surname,
            username = username,
            description = description,
            userId = userId,
            imgUrl = imgUrl
        )
    }
}