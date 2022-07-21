package com.organizee.api.controllers.guide.json

import com.organizee.guide.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val message: String,
    val createdAt: LocalDateTime
) {
    companion object {
        fun fromEntity(entity: Comment) = CommentResponse(
            message = entity.message,
            createdAt = entity.createdAt
        )
    }
}