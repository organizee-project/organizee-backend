package com.organizee.web.controllers.comment.json.payloads

import com.organizee.usecases.guide.commands.NewCommentCommand

data class CreateCommentPayload(
    val message: String
) {
    fun toUseCaseInput(slug: String, userId: String) =
        NewCommentCommand(message = message, slug = slug, userId = userId)
}
