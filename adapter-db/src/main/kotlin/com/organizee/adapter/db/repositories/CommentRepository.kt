package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.CommentEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CommentRepository : PagingAndSortingRepository<CommentEntity, UUID> {
    fun findAllByGuideSlug(guideSlug: String, pageable: Pageable): Page<CommentEntity>

    @Query("DELETE FROM CommentEntity c WHERE c.id = :id OR c.referencedComment = :id ")
    @Modifying
    fun deleteComments(id: UUID)
}