package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.LikeEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LikeRepository : PagingAndSortingRepository<LikeEntity, UUID> {
    fun findAllByGuideSlug(guideSlug: String, pageable: Pageable): Page<LikeEntity>
}