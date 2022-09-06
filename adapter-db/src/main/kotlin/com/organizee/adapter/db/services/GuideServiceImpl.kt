package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.GuideEntity
import com.organizee.adapter.db.repositories.CategoryRepository
import com.organizee.adapter.db.repositories.GuideCustomRepository
import com.organizee.adapter.db.repositories.GuideRepository
import com.organizee.boundaries.db.entities.FilterGuide
import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.guide.Guide
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class GuideServiceImpl(
    private val repository: GuideRepository,
    private val categoryRepository: CategoryRepository,
    private val customRepository: GuideCustomRepository
) : GuideService {

    override fun findAllFilteredBy(filter: FilterGuide): Page<Guide> {
        return customRepository.getFilteredGuides(filter).map { it.toEntity() }
    }

    override fun getGuide(slug: String): Guide =
        repository.findFirstBySlug(slug).toEntity()

    override fun removeGuide(slug: String) {
        val entity = repository.findFirstBySlug(slug)
        repository.delete(entity)
    }

    @Transactional
    override fun update(slug: String, updatedGuide: Guide): Guide {
        val entity = repository.findFirstBySlug(slug)

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
    override fun save(guide: Guide): Guide {
        val categories = categoryRepository.findByIdIn(guide.getCategoriesIds())
        val entity = GuideEntity.from(guide, categories)

        return repository.save(entity).toEntity()
    }

}