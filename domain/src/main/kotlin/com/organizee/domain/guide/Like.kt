package com.organizee.domain.guide

import com.organizee.domain.user.User
import java.time.LocalDateTime
import java.util.*


data class Like(
    val id: UUID,
    val user: User,
    val guide: Guide,
    val createdAt: LocalDateTime
) {
    companion object {
        operator fun invoke(user: User, guide: Guide) = Like(
            id = UUID.randomUUID(),
            user = user,
            guide = guide,
            createdAt = LocalDateTime.now()
        )
    }
}
