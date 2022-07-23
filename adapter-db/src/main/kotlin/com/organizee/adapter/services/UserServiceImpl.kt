package com.organizee.adapter.services

import com.organizee.boundary.db.entities.UserEntity
import com.organizee.boundary.db.repositories.UserRepository
import com.organizee.boundary.db.services.UserService
import com.organizee.user.User
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