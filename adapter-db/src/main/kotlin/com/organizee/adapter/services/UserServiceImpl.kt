package com.organizee.adapter.services

import com.organizee.boundary.db.entities.UserEntity
import com.organizee.boundary.db.repositories.UserRepository
import com.organizee.boundary.db.services.UserService
import com.organizee.exceptions.BussinessException
import com.organizee.user.User
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    @Transactional
    override fun create(user: User): User {
        validateUser(user.email, user.username)

        return userRepository.save(UserEntity.from(user)).toEntity()
    }


    private fun validateUser(email: String, username: String) {
        userRepository.findByEmail(email)
            .ifPresent { throw BussinessException("Email already in use") }

        userRepository.findByUsername(username)
            .ifPresent { throw BussinessException("Username already in use") }
    }
}