package com.organizee.api.controllers.user.json

import com.organizee.user.User

data class UserResponse(
    val name: String,
    val surname: String,
    val username: String,
    val email: String,
) {
    companion object {
        fun fromEntity(user: User) = UserResponse(
            name = user.name,
            surname = user.surname,
            username = user.username,
            email = user.email
        )
    }
}