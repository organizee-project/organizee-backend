package com.organizee.usecases.user.commands

data class FollowUserCommand(
    val userId: String,
    val username: String
)
