package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.CommentEntity
import com.organizee.adapter.db.repositories.CommentRepository
import com.organizee.adapter.db.repositories.GuideRepository
import com.organizee.adapter.db.repositories.UserRepository
import com.organizee.boundaries.db.services.CommentService
import com.organizee.domain.Page
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.domain.guide.Comment
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CommentServiceImpl(
    private val repository: CommentRepository,
    private val guideRepository: GuideRepository,
    private val userRepository: UserRepository
) : CommentService {
    override fun getCommentByIdOrThrow(id: UUID): Comment {
        return repository.findByIdOrNull(id)?.toEntity()
            ?: throw ErrorCodes.COMMENT_NOT_FOUND()
    }

    override fun create(comment: Comment): Comment {
        val guide = guideRepository.findFirstBySlug(comment.guide.slug)
            ?: throw ErrorCodes.GUIDE_NOT_FOUND(listOf(comment.guide.slug))
        val user = userRepository.findByUsername(comment.user.username)
            ?: throw ErrorCodes.USER_NOT_FOUND(listOf(comment.user.username))

        return repository.save(CommentEntity.from(comment, guide, user)).toEntity()
    }

    override fun getComments(slug: String, page: Int, size: Int): Page<Comment> {

        val response = repository.findAllByGuideSlugAndReferencedCommentIsNull(
            slug,
            PageRequest.of(page, size)
        )

        val page = response.map {
            val count = repository.countAllByReferencedComment(it.id)
            it.toEntity(count)
        }

        return Page.of(page)
    }

    override fun getCommentsByReferencedCommentId(id: UUID, page: Int, size: Int): Page<Comment> {
        val response = repository.findAllByReferencedComment(id, PageRequest.of(page, size))

        return Page.of(response.map {
            it.toEntity()
        })
    }

    @Transactional
    override fun deleteById(id: UUID) {
        repository.deleteComments(id)
    }
}