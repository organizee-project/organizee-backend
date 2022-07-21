package com.organizee.api.controllers.guide.json

import com.organizee.guide.Guide
import java.time.LocalDateTime

data class GuideResponse(
    val title: String,
    val slug: String,
    val subtitle: String,
    val content: String,
    val type: String,
    val createdAt: LocalDateTime
) {
    companion object {
        fun fromEntity(entity: Guide) = GuideResponse(
            title = entity.title,
            slug = entity.slug,
            subtitle = entity.subtitle,
            content = entity.content,
            type = entity.type.name,
            createdAt = entity.createdAt
        )
    }
}
