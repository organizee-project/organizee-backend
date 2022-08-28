package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.GuideEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GuideRepository : JpaRepository<GuideEntity, UUID> {
    fun findFirstBySlug(slug: String): GuideEntity
}