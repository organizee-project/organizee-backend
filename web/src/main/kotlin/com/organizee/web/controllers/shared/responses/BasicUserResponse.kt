package com.organizee.web.controllers.shared.responses

import com.organizee.domain.user.User

data class BasicUserResponse(
    val imgUrl: String = "",
    val username: String,
    val name: String,
) {
    companion object {
        fun fromEntity(user: User) =
            BasicUserResponse(user.imgUrl, user.username, user.getFullName())
    }
}