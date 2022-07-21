package com.organizee.guide

import com.organizee.boundary.shared.utils.toSlug
import java.time.LocalDateTime

data class Guide(
    val title: String,
    val slug: String,
    val subtitle: String,
    val content: String,
    val type: GuideType,
    val createdAt: LocalDateTime
) {
    companion object {
        fun create(title: String, subtitle: String, content: String, isPrivate: Boolean) = Guide(
            title = title,
            slug = title.toSlug(),
            subtitle = subtitle,
            content = content,
            type = when (isPrivate) {
                true -> GuideType.PRIVATE
                else -> GuideType.PUBLIC
            },
            createdAt = LocalDateTime.now()
        )
    }
}
