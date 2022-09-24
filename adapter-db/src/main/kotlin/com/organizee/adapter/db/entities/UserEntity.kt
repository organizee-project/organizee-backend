package com.organizee.adapter.db.entities

import com.organizee.domain.user.User
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tb_user")
data class UserEntity(
    @Id
    val id: UUID,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val surname: String,
    @Column(unique = true, nullable = false)
    val username: String,
    @Column(unique = true, nullable = false)
    val email: String,
    @Column(nullable = false)
    val password: String,

    @Column
    val createdAt: LocalDateTime

) : Serializable {
    companion object {
        fun from(user: User): UserEntity =
            UserEntity(
                id = UUID.randomUUID(),
                name = user.name,
                surname = user.surname,
                email = user.email,
                username = user.username,
                password = user.password,
                createdAt = LocalDateTime.now()
            )
    }

    fun toEntity() =
        User(
            email = email,
            name = name,
            surname = surname,
            username = username,
            password = this.password,
        )
}