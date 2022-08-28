package com.organizee.adapter.db.services

import com.organizee.adapter.db.entities.CategoryEntity
import com.organizee.adapter.db.entities.GuideEntity
import com.organizee.adapter.db.repositories.CategoryRepository
import com.organizee.adapter.db.repositories.GuideRepository
import com.organizee.boundaries.db.services.CategoryService
import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.guide.Guide
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class GuideServiceImpl(
    private val repository: GuideRepository,
    private val categoryService: CategoryService,
    private val categoryRepository: CategoryRepository
) : GuideService {
    @Transactional
    override fun save(guide: Guide): Guide {
        val categories = getCategoriesEntites(guide)

        return repository.save(GuideEntity.from(guide, categories)).toEntity()
    }

    override fun findAll(pegeable: Pageable): Page<Guide> {
        return repository.findAll(pegeable).map { it.toEntity() }
    }

    override fun getGuide(slug: String): Guide =
        repository.findFirstBySlug(slug).toEntity()

    override fun removeGuide(slug: String) {
        val entity = repository.findFirstBySlug(slug)
        repository.delete(entity)
    }

    override fun update(slug: String, updatedGuide: Guide): Guide {
        val entity = repository.findFirstBySlug(slug)

        val categories = getCategoriesEntites(updatedGuide)

        val updatedEntity = entity.copy(
            title = updatedGuide.title,
            slug = updatedGuide.slug,
            subtitle = updatedGuide.subtitle,
            content = updatedGuide.content,
            type = updatedGuide.type.toString(),
            categories = categories,
            updatedAt = LocalDateTime.now()
        )

        return repository.save(updatedEntity).toEntity()
    }

    private fun getCategoriesEntites(guide: Guide): List<CategoryEntity> {
        val notCreatedCategories = guide.categories.filter { it.id == null }

        val categories = guide.categories.filter { it.id != null }.toMutableList()

        if (notCreatedCategories.isNotEmpty())
            categories.addAll(categoryService.saveAll(notCreatedCategories))

        return categoryRepository.findByIdIn(categories.map { it.id!! })
    }

}