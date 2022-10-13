package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.UserEntity
import com.organizee.adapter.db.repositories.UserRepository
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.user.User
import org.springframework.data.repository.findByIdOrNull
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

    override fun findByUserIdOrThrow(userId: String): User {
        return userRepository.findByIdOrNull(userId)?.toEntity()
            ?: throw IllegalStateException("User not found")
    }

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)?.toEntity()
    }

    override fun userExists(username: String): Boolean {
        return when (userRepository.findByUsername(username)) {
            null -> false
            else -> true
        }
    }

    override fun findById(id: String) =
        userRepository.findByIdOrNull(id)?.toEntity()

}