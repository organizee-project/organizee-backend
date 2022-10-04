package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.LikeEntity
import com.organizee.adapter.db.repositories.GuideRepository
import com.organizee.adapter.db.repositories.LikeRepository
import com.organizee.adapter.db.repositories.UserRepository
import com.organizee.boundaries.db.services.LikeService
import com.organizee.domain.guide.Like
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl(
    private val repository: LikeRepository,
    private val guideRepository: GuideRepository,
    private val userRepository: UserRepository
) : LikeService {


    override fun add(like: Like): Like {
        val guide = guideRepository.findFirstBySlug(like.guide.slug)
            ?: throw IllegalStateException("No guide found for slug ${like.guide.slug}")
        val user = userRepository.findByUsername(like.user.username)
            ?: throw IllegalStateException("No user found for username ${like.guide.slug}")

        return repository.save(LikeEntity.from(like, guide, user)).toEntity()
    }
}