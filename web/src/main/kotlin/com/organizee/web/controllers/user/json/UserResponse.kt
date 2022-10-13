package com.organizee.web.controllers.user.json

import com.organizee.domain.user.User

data class UserResponse(
    val name: String,
    val surname: String,
    val username: String,
    val description: String
) {
    companion object {
        fun fromEntity(user: User) = UserResponse(
            name = user.name,
            surname = user.surname,
            username = user.username,
            description = user.description
        )
    }
}