package com.organizee.boundary.db.entities

import com.organizee.Guide
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
    @Column
    val subtitle: String,
    @Column
    val content: String,
    @Column
    val createdAt: LocalDateTime
) : Serializable {
    companion object {
        fun from(guide: Guide): GuideEntity =
            GuideEntity(
                id = UUID.randomUUID(),
                title = guide.title,
                subtitle = guide.subtitle,
                content = guide.content,
                createdAt = guide.createdAt
            )
    }

    fun toEntity() =
        Guide(title = title, subtitle = subtitle, content = content, createdAt = createdAt)
}