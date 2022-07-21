package com.organizee.adapter.services

import com.organizee.boundary.db.entities.CommentEntity
import com.organizee.boundary.db.repositories.CommentRepository
import com.organizee.boundary.db.repositories.GuideRepository
import com.organizee.boundary.db.services.CommentService
import com.organizee.guide.Comment
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val repository: CommentRepository,
    private val guideRepository: GuideRepository
) : CommentService {
    override fun create(comment: Comment, guideSlug: String): Comment {
        val guide = guideRepository.findFirstBySlug(slug = guideSlug)

        return repository.save(CommentEntity.from(comment, guide)).toEntity()
    }
}