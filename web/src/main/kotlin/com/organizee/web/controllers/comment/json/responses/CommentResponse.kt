package com.organizee.web.controllers.comment.json.responses

import com.organizee.domain.guide.Comment
import com.organizee.web.controllers.shared.responses.BasicUserResponse
import java.time.LocalDateTime

data class CommentResponse(
    val user: BasicUserResponse,
    val message: String,
    val createdAt: LocalDateTime?
) {
    companion object {
        fun fromEntity(entity: Comment) = CommentResponse(
            user = BasicUserResponse(username = entity.user.username),
            message = entity.message,
            createdAt = entity.createdAt
        )
    }
}