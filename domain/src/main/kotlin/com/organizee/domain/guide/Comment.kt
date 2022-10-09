package com.organizee.domain.guide

import com.organizee.domain.user.User
import java.time.LocalDateTime
import java.util.*

data class Comment(
    val id: UUID,
    val message: String,
    val user: User,
    val guide: Guide,
    val createdAt: LocalDateTime
) {
    companion object {
        fun create(message: String, user: User, guide: Guide) = Comment(
            id = UUID.randomUUID(),
            message = message,
            user = user,
            guide = guide,
            createdAt = LocalDateTime.now()
        )
    }

    fun checkUser(userId: String) = userId == user.id
}
