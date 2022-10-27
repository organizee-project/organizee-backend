package com.organizee.domain.user

data class User(
    val id: String,
    val name: String,
    val surname: String,
    val username: String,
    val description: String,
    val imgUrl: String = "",
    val following: List<User> = emptyList(),
    val followers: List<User> = emptyList()
) {
    companion object {
        fun createNormalUser(
            id: String,
            name: String,
            surname: String,
            username: String,
            description: String?,
            imgUrl: String?
        ) = User(
            id = id,
            name = name,
            surname = surname,
            username = username,
            description = description ?: "",
            imgUrl = imgUrl ?: ""
        )
    }

    fun getFullName() = "$name $surname"
    fun update(name: String?, surname: String?, description: String?, imgUrl: String?): User =
        copy(
            name = name ?: this.name,
            surname = surname ?: this.surname,
            description = description ?: this.description,
            imgUrl = imgUrl ?: this.imgUrl
        )
}
