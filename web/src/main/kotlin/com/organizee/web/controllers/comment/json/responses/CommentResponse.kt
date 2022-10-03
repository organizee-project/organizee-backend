package com.organizee.web.controllers.comment.json.responses

import com.organizee.domain.guide.Comment
import java.time.LocalDateTime

data class CommentResponse(
    val user: CommentUserResponse,
    val message: String,
    val createdAt: LocalDateTime?
) {
    companion object {
        fun fromEntity(entity: Comment) = CommentResponse(
            user = CommentUserResponse(username = entity.user.username),
            message = entity.message,
            createdAt = entity.createdAt
        )
    }
}


data class CommentUserResponse(
    val imgUrl: String = "",
    val username: String
)