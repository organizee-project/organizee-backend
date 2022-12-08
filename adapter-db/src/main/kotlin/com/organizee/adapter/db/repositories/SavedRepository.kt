package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.SavedEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SavedRepository : PagingAndSortingRepository<SavedEntity, UUID> {
    fun findAllByUserUsername(username: String, pageable: Pageable): Page<SavedEntity>

    fun findFirstByUserIdAndGuideSlug(userId: String, guideSlug: String): SavedEntity?

}