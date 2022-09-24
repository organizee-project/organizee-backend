package com.organizee.firebase.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import com.organizee.boundaries.firebase.UserService
import com.organizee.domain.user.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserFirebaseServiceImpl(private val firebaseAuth: FirebaseAuth) : UserService {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun createUser(user: User) {
        val request = UserRecord.CreateRequest()
            .setEmail(user.email)
            .setPassword(user.password)
            .setDisplayName(user.username)

        val createdUser = firebaseAuth.createUser(request)

        logger.info("User created | user: $createdUser ")
    }

}