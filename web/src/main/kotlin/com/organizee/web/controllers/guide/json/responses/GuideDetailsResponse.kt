package com.organizee.web.controllers.guide.json.responses

import com.organizee.domain.guide.Guide
import com.organizee.domain.guide.Reference
import com.organizee.web.controllers.category.json.response.CategoryResponse
import java.time.LocalDateTime
import java.util.*

data class GuideDetailsResponse(
    val title: String,
    val slug: String,
    val subtitle: String,
    val content: String,
    val type: String,
    val categories: List<CategoryResponse> = emptyList(),
    val topics: List<String> = emptyList(),
    val references: List<ReferenceResponse> = emptyList(),
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
) {
    companion object {
        fun fromEntity(entity: Guide) = GuideDetailsResponse(
            title = entity.title,
            slug = entity.slug,
            subtitle = entity.subtitle,
            content = entity.content,
            type = entity.type.name,
            categories = entity.categories.map {
                CategoryResponse(id = it.id, name = it.name, slug = it.slug)
            },
            topics = entity.topics,
            references = entity.references.map { ReferenceResponse.fromEntity(it) },
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt,
        )
    }
}

data class ReferenceResponse(
    val id: UUID,
    val title: String,
    val url: String
) {
    companion object {
        fun fromEntity(entity: Reference) =
            ReferenceResponse(id = entity.id, title = entity.title, url = entity.url)
    }
}
