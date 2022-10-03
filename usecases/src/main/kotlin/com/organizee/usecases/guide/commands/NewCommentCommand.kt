package com.organizee.usecases.guide.commands

data class NewCommentCommand(
    val userId: String,
    val message: String,
    val slug: String
)
