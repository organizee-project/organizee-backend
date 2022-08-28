package com.organizee.web.controllers.guide.json

import com.organizee.usecases.guide.commands.CategoryCommand
import com.organizee.usecases.guide.commands.UpdateGuideCommand

data class UpdateGuidePayload(
    val title: String? = null,
    val subtitle: String? = null,
    val content: String? = null,
    val categories: List<CategoryPayload>? = null,
    val isPrivate: Boolean? = null
) {
    fun toUseCaseInput(slug: String) = UpdateGuideCommand(
        slug = slug,
        title = if (!title.isNullOrEmpty()) title else null,
        subtitle = if (!subtitle.isNullOrEmpty()) subtitle else null,
        content = if (!content.isNullOrEmpty()) content else null,
        categories = categories?.map {
            CategoryCommand(
                id = it.id,
                name = it.name
            )
        },
        isPrivate = isPrivate
    )
}

