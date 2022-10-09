package com.organizee.usecases.guide.commands

import java.util.*

data class RemoveCommentCommand(
    val userId: String,
    val commentId: UUID
)
