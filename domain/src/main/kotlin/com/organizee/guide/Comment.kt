package com.organizee.guide

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
