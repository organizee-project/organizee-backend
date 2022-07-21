package com.organizee.boundary.db.entities

import com.organizee.guide.Comment
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class CommentEntity(
    @Id
    @GeneratedValue
    val id: UUID,
    @Column(nullable = false)
    val message: String,
    @Column
    val createdAt: LocalDateTime,
    @ManyToOne
    val guide: GuideEntity
) : Serializable {

    companion object {
        fun from(comment: Comment, guide: GuideEntity) =
            CommentEntity(
                id = UUID.randomUUID(),
                message = comment.message,
                createdAt = comment.createdAt,
                guide = guide
            )
    }

    fun toEntity() =
        Comment(
            message = message,
            createdAt = createdAt
        )
}