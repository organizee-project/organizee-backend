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
        return userRepository.save(UserEntity.from(user)).toEntity()
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)?.toEntity()
    }

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)?.toEntity()
    }
}