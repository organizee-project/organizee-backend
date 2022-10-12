package com.organizee.domain.guide

import com.organizee.domain.user.User
import java.time.LocalDateTime
import java.util.*

data class Comment(
    val id: UUID,
    val message: String,
    val user: User,
    val guide: Guide,
    val referencedComment: UUID? = null,
    val createdAt: LocalDateTime,
    val commentsCount: Int = 0
) {
    companion object {
        fun create(message: String, user: User, guide: Guide, referencedComment: UUID?) = Comment(
            id = UUID.randomUUID(),
            message = message,
            user = user,
            guide = guide,
            referencedComment = referencedComment,
            createdAt = LocalDateTime.now()
        )
    }

    fun hasReferencedComment() = referencedComment != null

    fun checkUser(userId: String) = userId == user.id
}
