package com.organizee.web.controllers.guide.json.payloads

import com.organizee.usecases.guide.commands.NewCommentCommand

data class CreateCommentPayload(
    val message: String
) {
    fun toUseCaseInput(slug: String) = NewCommentCommand(message = message, slug = slug)
}
