package com.organizee.domain.user

data class User(
    val id: String,
    val name: String,
    val surname: String,
    val username: String,
    val description: String,
    val imgUrl: String = "",
) {
    companion object {
        fun createNormalUser(
            id: String,
            name: String,
            surname: String,
            username: String,
            description: String
        ) = User(
            id = id,
            name = name,
            surname = surname,
            username = username,
            description = description
        )
    }

    fun getFullName() = "$name $surname"
}
