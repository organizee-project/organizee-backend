package com.organizee.adapter.db.entities

import com.organizee.domain.activity.Activity
import com.organizee.domain.activity.ActivityType
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_activity")
data class ActivityEntity(
    @Id
    val id: UUID,

    @Column
    val type: String,

    @Column
    val referenceId: String,

    @ManyToOne
    val user: UserEntity,
    @Column
    val createdAt: LocalDateTime
) : Serializable {

    companion object {
        fun from(activity: Activity, user: UserEntity) =
            ActivityEntity(
                id = activity.id,
                type = activity.type.name,
                referenceId = activity.referenceId,
                user = user,
                createdAt = activity.date
            )
    }

    fun toEntity() =
        Activity(
            id = id,
            date = createdAt,
            type = ActivityType.valueOf(type),
            referenceId = referenceId,
            user = user.toEntity()
        )
}