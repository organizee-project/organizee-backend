package com.organizee.web.controllers.guide.json.payloads

import com.organizee.usecases.guide.commands.UpdateGuideCommand

data class UpdateGuidePayload(
    val title: String? = null,
    val subtitle: String? = null,
    val content: String? = null,
    val categories: List<Long>? = null,
    val topics: List<String>? = null,
    val isPrivate: Boolean? = null
) {
    fun toUseCaseInput(slug: String) = UpdateGuideCommand(
        slug = slug,
        title = if (!title.isNullOrEmpty()) title else null,
        subtitle = if (!subtitle.isNullOrEmpty()) subtitle else null,
        content = if (!content.isNullOrEmpty()) content else null,
        topics = topics,
        categories = categories,
        isPrivate = isPrivate
    )
}

