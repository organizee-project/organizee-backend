package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.SavedEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SavedRepository : JpaRepository<SavedEntity, UUID> {

    fun findFirstByUserIdAndGuideSlug(userId: String, guideSlug: String): SavedEntity?

}