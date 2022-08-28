package com.organizee.domain.guide

import java.time.LocalDateTime

data class Comment(
    val message: String,
    val createdAt: LocalDateTime? = null
) {
    companion object {
        fun create(message: String) = Comment(
            message = message,
        )
    }
}
