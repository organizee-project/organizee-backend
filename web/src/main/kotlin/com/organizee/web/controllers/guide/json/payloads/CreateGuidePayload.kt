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
    val imgUrl: String? = "",
    val categories: List<Long>? = emptyList(),
    val topics: List<String>? = emptyList(),
    val references: List<CreateReferencePayload> = emptyList(),
    val isPrivate: Boolean = false
) {
    fun toUseCaseInput(userId: String) = NewGuideCommand(
        title = title,
        subtitle = subtitle,
        content = content,
        categories = categories ?: emptyList(),
        references = references.map { NewReferenceCommand(it.url) },
        topics = topics ?: emptyList(),
        userId = userId,
        isPrivate = isPrivate,
        imgUrl = imgUrl ?: "",
    )
}


data class CreateReferencePayload(
    val url: String
)