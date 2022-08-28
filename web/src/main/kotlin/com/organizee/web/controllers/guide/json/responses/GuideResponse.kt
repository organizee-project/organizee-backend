package com.organizee.web.controllers.guide.json.responses

import com.organizee.domain.guide.Guide
import java.time.LocalDateTime

data class GuideResponse(
    val title: String,
    val slug: String,
    val subtitle: String,
    val type: String,
    val categories: List<CategoryResponse> = emptyList(),
    val createdAt: LocalDateTime?
) {
    companion object {
        fun fromEntity(entity: Guide) = GuideResponse(
            title = entity.title,
            slug = entity.slug,
            subtitle = entity.subtitle,
            type = entity.type.name,
            categories = entity.categories.map {
                CategoryResponse(id = it.id, name = it.name, slug = it.slug)
            },
            createdAt = entity.createdAt
        )
    }
}