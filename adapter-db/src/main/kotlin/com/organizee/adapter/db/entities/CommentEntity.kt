package com.organizee.adapter.db.entities

import com.organizee.domain.guide.Comment
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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
    val createdAt: LocalDateTime,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    val user: UserEntity
) : Serializable {

    companion object {
        fun from(comment: Comment, guide: GuideEntity, user: UserEntity) =
            CommentEntity(
                id = comment.id,
                message = comment.message,
                guide = guide,
                createdAt = comment.createdAt,
                user = user
            )
    }

    fun toEntity() =
        Comment(
            message = message,
            createdAt = createdAt,
            id = id,
            user = user.toEntity(),
            guide = guide.toEntity()
        )
}