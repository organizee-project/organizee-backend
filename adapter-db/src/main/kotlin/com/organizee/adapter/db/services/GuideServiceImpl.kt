package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.GuideEntity
import com.organizee.adapter.db.entities.SavedEntity
import com.organizee.adapter.db.repositories.*
import com.organizee.boundaries.db.entities.FilterGuide
import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.guide.Guide
import com.organizee.domain.guide.GuideType
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class GuideServiceImpl(
    private val repository: GuideRepository,
    private val likeRepository: LikeRepository,
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository,
    private val customRepository: GuideCustomRepository,
    private val savedRepository: SavedRepository
) : GuideService {
    override fun findAllByUser(username: String, page: Int, size: Int): Page<Guide> {
        return repository.findAllByUserUsername(username, PageRequest.of(page, size))
            .map { it.toEntity() }
    }

    override fun findAllPublicByUser(username: String, page: Int, size: Int): Page<Guide> {
        return repository.findAllByUserUsernameAndType(
            username,
            GuideType.PUBLIC.name,
            PageRequest.of(page, size)
        )
            .map { it.toEntity() }
    }

    override fun findSaved(userId: String, page: Int, size: Int): Page<Guide> {
        return savedRepository.findAllByUserId(userId, PageRequest.of(page, size))
            .map { it.guide.toEntity() }
    }

    override fun isSaved(userId: String, slug: String): Boolean {
        return savedRepository.findFirstByUserIdAndGuideSlug(userId, slug) != null
    }

    override fun findAllFilteredBy(filter: FilterGuide): Page<Guide> {
        return customRepository.getFilteredGuides(filter).map { it.toEntity() }
    }

    override fun getGuideBySlugOrThrow(slug: String): Guide =
        repository.findFirstBySlug(slug)?.toEntity()
            ?: throw IllegalStateException("Guide not found: $slug")

    override fun getAllPublicByUserId(userId: String): List<Guide> {
        return repository.findAllPublicByUserId(userId).map { it.toEntity() }
    }

    override fun removeGuide(slug: String) {
        val entity = repository.findFirstBySlug(slug)
        repository.delete(entity)
    }

    @Transactional
    override fun update(slug: String, updatedGuide: Guide): Guide {
        val entity =
            repository.findFirstBySlug(slug) ?: throw IllegalStateException("Slug not found")

        val categories = categoryRepository.findByIdIn(updatedGuide.getCategoriesIds())

        val updatedEntity = entity.copy(
            title = updatedGuide.title,
            slug = updatedGuide.slug,
            subtitle = updatedGuide.subtitle,
            content = updatedGuide.content,
            type = updatedGuide.type.toString(),
            categories = categories,
            topics = updatedGuide.topics,
            updatedAt = LocalDateTime.now(),
        )

        return repository.save(updatedEntity).toEntity()
    }

    @Transactional
    override fun save(guide: Guide, userId: String): Guide {


        val user = userRepository.findByIdOrNull(userId) ?: throw Exception("")
        val categories = categoryRepository.findByIdIn(guide.getCategoriesIds())
        val entity = GuideEntity.from(guide, user, categories)

        return repository.save(entity).toEntity()
    }

    override fun getLikedByUser(username: String, page: Int, size: Int): Page<Guide> {
        return likeRepository.findAllByUserUsernameAndGuideType(
            username,
            GuideType.PUBLIC.name,
            PageRequest.of(page, size)
        ).map { it.guide.toEntity() }
    }

    @Transactional
    override fun guideSavedByUser(slug: String, userId: String) {
        val guideEntity =
            repository.findFirstBySlug(slug) ?: throw IllegalStateException("Slug not found")
        val userEntity =
            userRepository.findByIdOrNull(userId) ?: throw Exception("")

        savedRepository.save(SavedEntity.from(guideEntity, userEntity))
    }

    override fun isLiked(userId: String, slug: String): Boolean {
        return repository.findFirstBySlugAndLikesUserId(slug, userId) != null
    }

}