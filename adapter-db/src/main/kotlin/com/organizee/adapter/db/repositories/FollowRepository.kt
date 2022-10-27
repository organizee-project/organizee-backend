package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.FollowersEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FollowRepository : JpaRepository<FollowersEntity, UUID> {

    fun findByFromUsernameAndToUsername(username: String, unfollowUser: String): FollowersEntity?
    fun findFirstByFromIdAndToUsername(id: String, follow: String): FollowersEntity?
}