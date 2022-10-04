package com.organizee.adapter.db.entities

import com.organizee.domain.guide.Like
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_like")
data class LikeEntity(
    @Id
    val id: UUID,
    @ManyToOne
    val user: UserEntity,
    @ManyToOne
    val guide: GuideEntity,
    @Column
    val createdAt: LocalDateTime
) : Serializable {

    companion object {
        fun from(like: Like, guide: GuideEntity, user: UserEntity) =
            LikeEntity(
                id = like.id,
                guide = guide,
                user = user,
                createdAt = like.createdAt
            )
    }

    fun toEntity() =
        Like(id = id, user = user.toEntity(), guide = guide.toEntity(), createdAt = createdAt)
}