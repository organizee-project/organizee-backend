package com.organizee.usecases.user.commands

data class UpdateUserCommand(
    val name: String?,
    val surname: String?,
    val description: String?,
    val imgUrl: String?,
    val userId: String
)