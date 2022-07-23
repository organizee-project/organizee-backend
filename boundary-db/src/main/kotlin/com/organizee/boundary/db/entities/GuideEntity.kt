package com.organizee.boundary.db.entities

import com.organizee.guide.Category
import com.organizee.guide.Comment
import com.organizee.guide.Guide
import com.organizee.guide.GuideType
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_guide")
data class GuideEntity(
    @Id
    val id: UUID,
    @Column(nullable = false)
    val title: String,
    @Column(nullable = false)
    val slug: String,
    @Column
    val subtitle: String,
    @Column
    val content: String,
    @Column
    val type: String,

    @Column
    @ManyToMany
    val categories: List<CategoryEntity> = emptyList(),

    @Column
    @OneToMany(mappedBy = "guide")
    val comments: List<CommentEntity> = emptyList(),

    @Column
    val createdAt: LocalDateTime

) : Serializable {
    companion object {
        fun from(guide: Guide, categories: List<CategoryEntity>): GuideEntity =
            GuideEntity(
                id = UUID.randomUUID(),
                title = guide.title,
                slug = guide.slug,
                subtitle = guide.subtitle,
                content = guide.content,
                type = guide.type.name,
                categories = categories,
                createdAt = LocalDateTime.now()
            )
    }

    fun toEntity() =
        Guide(
            title = title,
            subtitle = subtitle,
            content = content,
            slug = slug,
            type = GuideType.valueOf(type),
            comments = comments.map { Comment(message = it.message, createdAt = it.createdAt) },
            categories = categories.map { Category(title = it.title, slug = it.slug) },
            createdAt = createdAt
        )
}