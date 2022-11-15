package com.organizee.usecases.activity.commands

data class GetUserActivitiesCommand(
    val username: String,
    val page: Int,
    val size: Int
)
