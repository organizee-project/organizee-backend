package com.organizee.web.controllers.guide.json.responses

import com.organizee.domain.guide.Guide
import com.organizee.web.controllers.category.json.response.CategoryResponse
import com.organizee.web.controllers.shared.responses.BasicUserResponse
import java.time.LocalDateTime

data class GuideResponse(
    val title: String,
    val slug: String,
    val subtitle: String,
    val type: String,
    val categories: List<CategoryResponse> = emptyList(),
    val topics: List<String> = emptyList(),
    val likesCount: Int = 0,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
    val user: BasicUserResponse?
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
            likesCount = entity.likesCount,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
            user = entity.user?.let { BasicUserResponse.fromEntity(it) }
        )
    }
}