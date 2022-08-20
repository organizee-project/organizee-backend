package com.organizee.guide

import com.organizee.boundary.shared.utils.toSlug
import java.sql.Timestamp
import java.time.LocalDateTime

data class Guide(
    val title: String,
    val slug: String,
    val subtitle: String,
    val content: String,
    val type: GuideType,
    val categories: List<Category> = emptyList(),
    val comments: List<Comment> = emptyList(),
    val createdAt: LocalDateTime? = null
) {
    companion object {
        fun create(
            title: String,
            subtitle: String,
            content: String,
            isPrivate: Boolean,
            categories: List<Category>
        ) = Guide(
            title = title,
            slug = "${title.toSlug()}-${Timestamp.valueOf(LocalDateTime.now()).time}",
            subtitle = subtitle,
            content = content,
            categories = categories,
            type = when (isPrivate) {
                true -> GuideType.PRIVATE
                else -> GuideType.PUBLIC
            }
        )
    }
}
