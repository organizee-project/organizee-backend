package com.organizee.boundary.db.services

import com.organizee.user.User

interface UserService {
    fun create(user: User): User
}