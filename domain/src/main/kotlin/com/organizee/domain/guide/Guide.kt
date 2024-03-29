package com.organizee.domain.guide

import com.organizee.domain.formatter.toSlug
import com.organizee.domain.user.User
import java.sql.Timestamp
import java.time.LocalDateTime

data class Guide(
    val title: String,
    val imgUrl: String = "",
    val slug: String,
    val subtitle: String,
    val content: String,
    val type: GuideType,
    val user: User,
    val categories: List<Category> = emptyList(),
    val topics: List<String> = emptyList(),
    val comments: List<Comment> = emptyList(),
    val references: List<Reference> = emptyList(),
    val likesCount: Int = 0,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null
) {
    companion object {
        fun create(
            title: String,
            imgUrl: String,
            subtitle: String,
            content: String,
            isPrivate: Boolean,
            categories: List<Category>,
            references: List<Reference>,
            topics: List<String>,
            user: User
        ) = Guide(
            title = title,
            slug = createGuideSlug(title),
            subtitle = subtitle,
            content = content,
            categories = categories,
            type = GuideType.from(isPrivate),
            references = references,
            topics = topics,
            imgUrl = imgUrl,
            createdAt = LocalDateTime.now(),
            user = user
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
        imgUrl: String?
    ) = copy(
        title = title ?: this.title,
        slug = title?.let { createGuideSlug(title) } ?: this.slug,
        subtitle = subtitle ?: this.subtitle,
        content = content ?: this.content,
        type = isPrivate?.let { GuideType.from(it) } ?: this.type,
        categories = categories ?: this.categories,
        topics = topics ?: this.topics,
        imgUrl = imgUrl ?: this.imgUrl
    )

    fun getCategoriesIds() = this.categories.mapNotNull { it.id }

    fun isPrivate() = type == GuideType.PRIVATE
}
