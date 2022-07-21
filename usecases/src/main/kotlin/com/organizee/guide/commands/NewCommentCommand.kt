package com.organizee.guide.commands

data class NewCommentCommand(
    val message: String,
    val slug: String
)
