package com.organizee.adapter.db.entities

import com.organizee.domain.guide.Comment
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_comment")
data class CommentEntity(
    @Id
    @GeneratedValue
    val id: UUID,
    @Column(nullable = false)
    val message: String,
    @ManyToOne
    val guide: GuideEntity,
    @Column
    val createdAt: LocalDateTime
) : Serializable {

    companion object {
        fun from(comment: Comment, guide: GuideEntity) =
            CommentEntity(
                id = UUID.randomUUID(),
                message = comment.message,
                guide = guide,
                createdAt = LocalDateTime.now()
            )
    }

    fun toEntity() =
        Comment(
            message = message,
            createdAt = createdAt
        )
}