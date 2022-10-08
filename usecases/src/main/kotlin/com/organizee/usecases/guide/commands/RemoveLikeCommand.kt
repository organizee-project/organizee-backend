package com.organizee.usecases.guide.commands

data class RemoveLikeCommand(
    val userId: String,
    val slug: String
)
