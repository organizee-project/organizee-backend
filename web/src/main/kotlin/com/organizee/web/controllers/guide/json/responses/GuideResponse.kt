package com.organizee.web.controllers.guide.json.responses

import com.organizee.domain.guide.Guide
import com.organizee.web.controllers.category.json.response.CategoryResponse
import java.time.LocalDateTime

data class GuideResponse(
    val title: String,
    val slug: String,
    val subtitle: String,
    val type: String,
    val categories: List<CategoryResponse> = emptyList(),
    val topics: List<String> = emptyList(),
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
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
            topics = entity.topics,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }
}