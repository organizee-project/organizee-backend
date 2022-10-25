package com.organizee.adapter.db.entities

import com.organizee.domain.user.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_user")
class UserEntity(
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
    @Column(unique = false, nullable = false, columnDefinition = "varchar default ''")
    val imgUrl: String,
    @Column
    val createdAt: LocalDateTime,
    @OneToMany(mappedBy = "to", fetch = FetchType.LAZY)
    val followers: List<FollowersEntity> = emptyList(),
    @OneToMany(mappedBy = "from", fetch = FetchType.LAZY)
    val following: List<FollowersEntity> = emptyList(),


    ) {
    companion object {
        fun create(user: User): UserEntity =
            UserEntity(
                id = user.id,
                name = user.name,
                surname = user.surname,
                username = user.username,
                description = user.description,
                createdAt = LocalDateTime.now(),
                imgUrl = user.imgUrl
            )
    }

    fun toEntity() =
        User(
            id = id,
            imgUrl = imgUrl,
            name = name,
            surname = surname,
            username = username,
            description = description,
            following = following.map {
                User(
                    id = it.to.id,
                    name = it.to.name,
                    surname = it.to.surname,
                    username = it.to.username,
                    description = it.to.description
                )
            },
            followers = followers.map {
                User(
                    id = it.from.id,
                    name = it.from.name,
                    surname = it.from.surname,
                    username = it.from.username,
                    description = it.from.description
                )
            }
        )
}