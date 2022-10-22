package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.LikeEntity
import com.organizee.adapter.db.repositories.GuideRepository
import com.organizee.adapter.db.repositories.LikeRepository
import com.organizee.adapter.db.repositories.UserRepository
import com.organizee.boundaries.db.services.LikeService
import com.organizee.domain.Page
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.domain.guide.Like
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class LikeServiceImpl(
    private val repository: LikeRepository,
    private val guideRepository: GuideRepository,
    private val userRepository: UserRepository
) : LikeService {
    override fun findLikeByUsernameAndSlug(username: String, slug: String): Like? {
        return repository.findByUserUsernameAndGuideSlug(username, slug)?.toEntity()
    }


    override fun add(like: Like): Like {
        val guide = guideRepository.findFirstBySlug(like.guide.slug)
            ?: throw ErrorCodes.GUIDE_NOT_FOUND(listOf(like.guide.slug))

        val user = userRepository.findByUsername(like.user.username)
            ?: throw ErrorCodes.USER_NOT_FOUND(listOf(like.user.username))

        return repository.save(LikeEntity.from(like, guide, user)).toEntity()
    }

    override fun getLikes(slug: String, page: Int, size: Int): Page<Like> {
        val response = repository.findAllByGuideSlug(slug, PageRequest.of(page, size))

        val page = response.map {
            it.toEntity()
        }

        return Page.of(page)
    }

    override fun remove(id: UUID) {
        repository.deleteById(id)
    }
}