package com.organizee.boundary.db.entities

import com.organizee.guide.Guide
import com.organizee.guide.GuideType
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
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
    val createdAt: LocalDateTime
) : Serializable {
    companion object {
        fun from(guide: Guide): GuideEntity =
            GuideEntity(
                id = UUID.randomUUID(),
                title = guide.title,
                slug = guide.slug,
                subtitle = guide.subtitle,
                content = guide.content,
                type = guide.type.name,
                createdAt = guide.createdAt
            )
    }

    fun toEntity() =
        Guide(
            title = title,
            subtitle = subtitle,
            content = content,
            slug = slug,
            type = GuideType.valueOf(type),
            createdAt = createdAt
        )
}