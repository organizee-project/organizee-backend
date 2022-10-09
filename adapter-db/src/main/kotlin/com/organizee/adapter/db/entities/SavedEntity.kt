package com.organizee.adapter.db.entities

import com.organizee.domain.guide.Saved
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_saved_guides")
data class SavedEntity(
    @Id
    @GeneratedValue
    val id: UUID,
    @ManyToOne
    val guide: GuideEntity,
    @ManyToOne
    val user: UserEntity,
) : Serializable {

    companion object {
        fun from(guide: GuideEntity, user: UserEntity) =
            SavedEntity(
                id = UUID.randomUUID(),
                guide = guide,
                user = user
            )
    }

    fun toEntity() =
        Saved(
            id = id,
            user = user.toEntity(),
            guide = guide.toEntity()
        )
}