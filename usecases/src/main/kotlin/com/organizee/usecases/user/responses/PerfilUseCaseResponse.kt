package com.organizee.usecases.user.responses

import com.organizee.domain.guide.Guide
import com.organizee.domain.user.Activity

data class PerfilUseCaseResponse(
    val user: UserPerfilUseCaseResponse,
    val guides: List<Guide> = emptyList(),
    val likes: List<Guide> = emptyList(),
    val activities: List<Activity> = emptyList(),
)

data class UserPerfilUseCaseResponse(
    val imgUrl: String,
    val fullName: String,
    val username: String,
    val description: String
)


