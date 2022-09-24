package com.organizee.domain.user

data class User(
    val id: String? = null,
    val email: String,
    val name: String,
    val surname: String,
    val username: String,
) {
    companion object {
        fun createNormalUser(
            email: String,
            name: String,
            surname: String,
            username: String
        ) = User(
            email = email,
            name = name,
            surname = surname,
            username = username,
        )
    }
}
