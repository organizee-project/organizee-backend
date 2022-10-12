package com.organizee.web.controllers.comment.json.payloads

import com.organizee.usecases.guide.commands.NewCommentCommand
import java.util.*

data class CreateCommentPayload(
    val message: String,
    val referencedComment: String?
) {
    fun toUseCaseInput(slug: String, userId: String) =
        NewCommentCommand(
            message = message,
            slug = slug,
            userId = userId,
            referencedComment = referencedComment?.let { UUID.fromString(it) }
        )
}
