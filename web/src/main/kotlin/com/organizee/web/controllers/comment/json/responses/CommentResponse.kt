package com.organizee.web.controllers.comment.json.responses

import com.organizee.domain.guide.Comment
import com.organizee.web.controllers.shared.responses.BasicUserResponse
import java.time.LocalDateTime
import java.util.*

data class CommentResponse(
    val id: UUID,
    val user: BasicUserResponse,
    val message: String,
    val commentsCount: Int,
    val createdAt: LocalDateTime?
) {
    companion object {
        fun fromEntity(entity: Comment) = CommentResponse(
            id = entity.id,
            user = BasicUserResponse.fromEntity(entity.user),
            message = entity.message,
            commentsCount = entity.commentsCount,
            createdAt = entity.createdAt
        )
    }
}