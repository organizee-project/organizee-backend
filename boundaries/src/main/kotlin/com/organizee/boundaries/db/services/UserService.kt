package com.organizee.boundaries.db.services

import com.organizee.domain.user.User

interface UserService {
    fun update(user: User): User
    fun create(user: User): User
    fun findByUsernameOrThrow(username: String): User
    fun findByUserIdOrThrow(userId: String): User
    fun findByUsername(username: String): User?
    fun userExists(username: String): Boolean
    fun findById(id: String): User?
    fun follow(user: User, followUser: User)
    fun unfollow(user: User, followUser: User)
    fun userFollows(userId: String, followUserName: String): Boolean

}