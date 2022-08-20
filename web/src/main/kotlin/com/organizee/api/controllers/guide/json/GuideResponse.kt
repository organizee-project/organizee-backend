package com.organizee.api.controllers.guide.json

import com.organizee.guide.Guide
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

data class CategoryResponse(
    val id: Long?,
    val name: String,
    val slug: String
)