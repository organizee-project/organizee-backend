package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.CommentEntity
import com.organizee.adapter.db.repositories.CommentRepository
import com.organizee.adapter.db.repositories.GuideRepository
import com.organizee.boundaries.db.services.CommentService
import com.organizee.domain.guide.Comment
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