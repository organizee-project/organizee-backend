package com.organizee.boundaries.db.services

import com.organizee.domain.user.User

interface UserService {
    fun create(user: User): User
    fun findByEmail(email: String): User?
    fun findByUsername(username: String): User?
    fun userExists(email: String, username: String): Boolean
}