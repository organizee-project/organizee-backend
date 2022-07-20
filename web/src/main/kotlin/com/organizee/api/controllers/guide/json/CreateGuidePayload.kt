package com.organizee.api.controllers.guide.json

import com.organizee.guide.commands.NewGuideCommand

data class CreateGuidePayload(
    val title: String,
    val subtitle: String,
    val content: String
) {

    fun toUseCaseInput() = NewGuideCommand(title = title, subtitle = subtitle, content = content)

}
