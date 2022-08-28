package com.organizee.usecases.guide.commands

data class NewCommentCommand(
    val message: String,
    val slug: String
)
