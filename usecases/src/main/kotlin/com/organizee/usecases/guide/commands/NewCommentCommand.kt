package com.organizee.usecases.guide.commands

import java.util.*

data class NewCommentCommand(
    val userId: String,
    val message: String,
    val slug: String,
    val referencedComment: UUID? = null
)
