package com.organizee.usecases.user.commands

data class NewUserCommand(
    val email: String,
    val name: String,
    val surname: String,
    val username: String,
    val password: String,
)
