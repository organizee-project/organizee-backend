package com.organizee.web.controllers.user.json

import com.organizee.usecases.user.commands.UpdateUserCommand

data class UpdateUserPayload(
    val name: String? = null,
    val surname: String? = null,
    val description: String? = null,
    val imgUrl: String? = null
) {
    fun toUseCaseInput(userId: String) =
        UpdateUserCommand(
            name = name,
            surname = surname,
            description = description,
            userId = userId,
            imgUrl = imgUrl
        )
}
