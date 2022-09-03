package com.organizee.web.controllers.guide.json.payloads

import com.organizee.usecases.guide.commands.NewGuideCommand
import javax.validation.constraints.NotBlank

data class CreateGuidePayload(
    @field:NotBlank(message = "Title is mandatory")
    val title: String,
    val subtitle: String,
    @field:NotBlank(message = "Content is mandatory")
    val content: String,
    val categories: List<Long> = emptyList(),
    val isPrivate: Boolean = false
) {
    fun toUseCaseInput() = NewGuideCommand(
        title = title,
        subtitle = subtitle,
        content = content,
        categories = categories,
        isPrivate = isPrivate
    )
}


