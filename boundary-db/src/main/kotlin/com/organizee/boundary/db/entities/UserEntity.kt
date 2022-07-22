package com.organizee.boundary.db.entities

import com.organizee.user.User
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UserEntity(
    @Id
    val id: UUID,
    @Column
    val name: String,
    @Column
    val surname: String,
    @Column
    val username: String,
    @Column
    val email: String,
    @Column
    val password: String,

    ) : Serializable, AudityEntity() {
    companion object {
        fun from(user: User): UserEntity =
            UserEntity(
                id = UUID.randomUUID(),
                name = user.name,
                surname = user.surname,
                email = user.email,
                username = user.username,
                password = user.password
            )
    }

    fun toEntity() =
        User(
            email = email,
            name = name,
            surname = surname,
            username = username,
            password = this.password,
            type = listOf()
        )
}