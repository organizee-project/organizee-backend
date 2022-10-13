package com.organizee.adapter.db.entities

import com.organizee.domain.user.User
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_user")
data class UserEntity(
    @Id
    val id: String,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val surname: String,
    @Column(nullable = false)
    val description: String,
    @Column(unique = true, nullable = false)
    val username: String,
    @Column
    val createdAt: LocalDateTime

) : Serializable {
    companion object {
        fun create(user: User): UserEntity =
            UserEntity(
                id = user.id,
                name = user.name,
                surname = user.surname,
                username = user.username,
                description = user.description,
                createdAt = LocalDateTime.now()
            )
    }

    fun toEntity() =
        User(
            id = id,
            name = name,
            surname = surname,
            username = username,
            description = description
        )
}