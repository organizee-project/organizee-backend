package com.organizee.usecases.user.responses

data class PerfilUseCaseResponse(
    val user: UserPerfilUseCaseResponse,
)

data class UserPerfilUseCaseResponse(
    val imgUrl: String,
    val fullName: String,
    val username: String,
    val description: String,
    val followers: List<UserPerfilUseCaseResponse> = emptyList(),
    val following: List<UserPerfilUseCaseResponse> = emptyList()
)


