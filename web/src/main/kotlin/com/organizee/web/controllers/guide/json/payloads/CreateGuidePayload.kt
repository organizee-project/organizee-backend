package com.organizee.web.controllers.guide.json.payloads

import com.organizee.usecases.guide.commands.CategoryCommand
import com.organizee.usecases.guide.commands.NewGuideCommand
import javax.validation.constraints.NotBlank

data class CreateGuidePayload(
    @field:NotBlank(message = "Title is mandatory")
    val title: String,
    @field:NotBlank(message = "Subtitle is mandatory")
    val subtitle: String,
    @field:NotBlank(message = "Content is mandatory")
    val content: String,
    val categories: List<CategoryPayload> = emptyList(),
    val isPrivate: Boolean = false
) {
    fun toUseCaseInput() = NewGuideCommand(
        title = title,
        subtitle = subtitle,
        content = content,
        categories = categories.map {
            CategoryCommand(
                id = it.id,
                name = it.name
            )
        },
        isPrivate = isPrivate
    )
}

