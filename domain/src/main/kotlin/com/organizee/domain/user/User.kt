package com.organizee.domain.user

data class User(
    val email: String,
    val name: String,
    val surname: String,
    val username: String,
    val password: String,
    val type: List<UserType> = listOf(UserType.NORMAL)
) {
    companion object {
        fun createNormalUser(
            email: String,
            name: String,
            surname: String,
            username: String,
            password: String
        ) = User(
            email = email,
            name = name,
            surname = surname,
            username = username,
            password = password,
            type = listOf(UserType.NORMAL)
        )
    }
}

enum class UserType {
    ADMIN,
    PROFESSOR,
    NORMAL
}