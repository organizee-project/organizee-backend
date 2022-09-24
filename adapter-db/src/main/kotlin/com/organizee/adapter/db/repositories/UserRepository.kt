package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {
    fun findByEmail(email: String): UserEntity?
    fun findByUsername(username: String): UserEntity?
    fun findByEmailOrUsername(email: String, username: String): UserEntity?
}