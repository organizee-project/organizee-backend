package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {
    fun findByUsername(username: String): UserEntity?
}