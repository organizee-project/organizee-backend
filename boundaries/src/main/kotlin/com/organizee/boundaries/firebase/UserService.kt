package com.organizee.boundaries.firebase

import com.organizee.domain.user.User

interface UserService {
    fun createUser(user: User, password: String): User
}