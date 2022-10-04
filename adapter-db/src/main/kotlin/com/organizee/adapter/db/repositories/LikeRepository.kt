package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.LikeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LikeRepository : JpaRepository<LikeEntity, UUID> {

}