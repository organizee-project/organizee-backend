package com.organizee.api.controllers.guide.json

import com.organizee.guide.commands.NewCommentCommand

data class CreateCommentPayload(
    val message: String
) {
    fun toUseCaseInput(slug: String) = NewCommentCommand(message = message, slug = slug)
}
