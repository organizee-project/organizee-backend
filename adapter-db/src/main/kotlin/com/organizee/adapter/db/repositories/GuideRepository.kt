package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.GuideEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GuideRepository : PagingAndSortingRepository<GuideEntity, UUID> {
    fun findFirstBySlug(slug: String): GuideEntity?

    fun findAllByUserUsername(
        username: String,
        pageable: Pageable
    ): Page<GuideEntity>

    fun findFirstBySlugAndLikesUserId(slug: String, userId: String): GuideEntity?

    fun findAllByUserUsernameAndType(
        username: String,
        type: String,
        pageable: Pageable
    ): Page<GuideEntity>

    @Query("SELECT g FROM GuideEntity g WHERE g.type = 'PUBLIC' AND g.user.id = :userId")
    fun findAllPublicByUserId(userId: String): List<GuideEntity>

}