package com.organizee.adapter.db.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tb_follower")
data class FollowersEntity(
    @Id
    val id: UUID,

    @ManyToOne
    @JoinColumn(name = "from_user_fk")
    val from: UserEntity,

    @ManyToOne
    @JoinColumn(name = "to_user_fk")
    val to: UserEntity
)
