package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.UserEntity
import com.organizee.adapter.db.repositories.UserRepository
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.user.User
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    @Transactional
    override fun create(user: User): User {
        return userRepository.save(UserEntity.create(user)).toEntity()
    }

    override fun findByUsernameOrThrow(username: String): User {
        return userRepository.findByUsername(username)?.toEntity()
            ?: throw IllegalStateException("User not found")
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)?.toEntity()
    }

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)?.toEntity()
    }

    override fun userExists(email: String, username: String): Boolean {
        return when (userRepository.findByEmailOrUsername(email, username)) {
            null -> false
            else -> true
        }
    }

}