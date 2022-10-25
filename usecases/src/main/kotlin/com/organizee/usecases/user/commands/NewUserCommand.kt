package com.organizee.usecases.user.commands

data class NewUserCommand(
    val name: String,
    val surname: String,
    val username: String,
    val description: String,
    val imgUrl: String,
    val userId: String
)