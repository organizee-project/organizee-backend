package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.GuideEntity
import com.organizee.adapter.db.repositories.CategoryRepository
import com.organizee.adapter.db.repositories.GuideCustomRepository
import com.organizee.adapter.db.repositories.GuideRepository
import com.organizee.adapter.db.repositories.UserRepository
import com.organizee.boundaries.db.entities.FilterGuide
import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.guide.Guide
import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class GuideServiceImpl(
    private val repository: GuideRepository,
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository,
    private val customRepository: GuideCustomRepository
) : GuideService {

    override fun findAllFilteredBy(filter: FilterGuide): Page<Guide> {
        return customRepository.getFilteredGuides(filter).map { it.toEntity() }
    }

    override fun getGuideBySlugOrThrow(slug: String): Guide =
        repository.findFirstBySlug(slug)?.toEntity()
            ?: throw IllegalStateException("Guide not found: $slug")

    override fun getAllPublicByUserId(userId: String): List<Guide> {
        return repository.findAllPublicByUserId(userId).map { it.toEntity() }
    }

    override fun getAllByUserId(userId: String): List<Guide> {
        return repository.findAllByUserId(userId).map { it.toEntity() }
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

}