package com.organizee.domain.guide

import com.organizee.domain.formatter.toSlug
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
    fun update(
        title: String?,
        subtitle: String?,
        content: String?,
        isPrivate: Boolean?,
        categories: List<Category>?
    ) = copy(
        title = title ?: this.title,
        slug = title?.let { createGuideSlug(title) } ?: this.slug,
        subtitle = subtitle ?: this.subtitle,
        content = content ?: this.content,
        type = isPrivate?.let { GuideType.from(it) } ?: this.type,
        categories = categories ?: this.categories
    )

    companion object {
        fun create(
            title: String,
            subtitle: String,
            content: String,
            isPrivate: Boolean,
            categories: List<Category>
        ) = Guide(
            title = title,
            slug = createGuideSlug(title),
            subtitle = subtitle,
            content = content,
            categories = categories,
            type = GuideType.from(isPrivate)
        )

        fun createGuideSlug(title: String) =
            "${title.toSlug()}-${Timestamp.valueOf(LocalDateTime.now()).time}"
    }
}
