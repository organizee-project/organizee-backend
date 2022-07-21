package com.organizee.boundary.db.repositories

import com.organizee.boundary.db.entities.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CommentRepository : JpaRepository<CommentEntity, UUID> {
}