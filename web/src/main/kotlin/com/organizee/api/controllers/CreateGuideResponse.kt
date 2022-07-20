package com.organizee.api.controllers

import com.organizee.Guide
import java.time.LocalDateTime

data class CreateGuideResponse(
    val title: String,
    val subtitle: String,
    val content: String,
    val createdAt: LocalDateTime
) {
    companion object {
        fun fromEntity(entity: Guide) = CreateGuideResponse(
            title = entity.title,
            subtitle = entity.subtitle,
            content = entity.content,
            createdAt = entity.createdAt
        )
    }
}
