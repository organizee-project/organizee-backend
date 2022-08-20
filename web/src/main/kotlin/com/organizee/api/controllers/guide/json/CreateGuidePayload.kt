package com.organizee.api.controllers.guide.json

import com.organizee.guide.commands.NewCategoryCommand
import com.organizee.guide.commands.NewGuideCommand

data class CreateGuidePayload(
    val title: String,
    val subtitle: String,
    val content: String,
    val categories: List<CategoryPayload> = emptyList(),
    val isPrivate: Boolean
) {
    fun toUseCaseInput() = NewGuideCommand(
        title = title,
        subtitle = subtitle,
        content = content,
        categories = categories.map {
            NewCategoryCommand(
                id = it.id,
                name = it.name
            )
        },
        isPrivate = isPrivate
    )
}

data class CategoryPayload(
    val id: Long? = null,
    val name: String
)
