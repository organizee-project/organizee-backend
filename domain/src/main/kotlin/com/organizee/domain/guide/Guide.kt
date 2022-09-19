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
    val topics: List<String> = emptyList(),
    val comments: List<Comment> = emptyList(),
    val references: List<Reference> = emptyList(),
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
) {
    companion object {
        fun create(
            title: String,
            subtitle: String,
            content: String,
            isPrivate: Boolean,
            categories: List<Category>,
            references: List<Reference>,
            topics: List<String>
        ) = Guide(
            title = title,
            slug = createGuideSlug(title),
            subtitle = subtitle,
            content = content,
            categories = categories,
            type = GuideType.from(isPrivate),
            references = references,
            topics = topics
        )

        fun createGuideSlug(title: String) =
            "${title.toSlug()}-${Timestamp.valueOf(LocalDateTime.now()).time}"
    }

    fun update(
        title: String?,
        subtitle: String?,
        content: String?,
        isPrivate: Boolean?,
        categories: List<Category>?,
        topics: List<String>?,
    ) = copy(
        title = title ?: this.title,
        slug = title?.let { createGuideSlug(title) } ?: this.slug,
        subtitle = subtitle ?: this.subtitle,
        content = content ?: this.content,
        type = isPrivate?.let { GuideType.from(it) } ?: this.type,
        categories = categories ?: this.categories,
        topics = topics ?: this.topics
    )

    fun getCategoriesIds() = this.categories.mapNotNull { it.id }
}
