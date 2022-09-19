package com.organizee.web.controllers.guide.json.payloads

import com.organizee.usecases.guide.commands.NewGuideCommand
import com.organizee.usecases.guide.commands.NewReferenceCommand
import javax.validation.constraints.NotBlank

data class CreateGuidePayload(
    @field:NotBlank(message = "Title is mandatory")
    val title: String,
    val subtitle: String,
    @field:NotBlank(message = "Content is mandatory")
    val content: String,
    val categories: List<Long> = emptyList(),
    val topics: List<String> = emptyList(),
    val references: List<CreateReferencePayload>,
    val isPrivate: Boolean = false
) {
    fun toUseCaseInput() = NewGuideCommand(
        title = title,
        subtitle = subtitle,
        content = content,
        categories = categories,
        references = references.map { NewReferenceCommand(it.title, it.url) },
        topics = topics,
        isPrivate = isPrivate
    )
}


data class CreateReferencePayload(
    val title: String,
    val url: String
)