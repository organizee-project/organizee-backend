package com.organizee

import com.organizee.boundary.shared.utils.toSlug
import java.time.LocalDateTime

data class Guide(
    val title: String,
    val slug: String,
    val subtitle: String,
    val content: String,
    val createdAt: LocalDateTime
) {
    companion object {
        fun create(title: String, subtitle: String, content: String) = Guide(
            title = title,
            slug = title.toSlug(),
            subtitle = subtitle,
            content = content,
            createdAt = LocalDateTime.now()
        )
    }
}
