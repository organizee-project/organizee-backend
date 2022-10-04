package com.organizee.usecases.guide.commands

data class NewLikeCommand(
    val userId: String,
    val slug: String
)
