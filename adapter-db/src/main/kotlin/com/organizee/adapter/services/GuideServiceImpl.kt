package com.organizee.adapter.services

import com.organizee.boundary.db.entities.GuideEntity
import com.organizee.boundary.db.repositories.GuideRepository
import com.organizee.boundary.db.services.CategoryService
import com.organizee.boundary.db.services.GuideService
import com.organizee.guide.Guide
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class GuideServiceImpl(
    private val repository: GuideRepository,
    private val categoryService: CategoryService
) : GuideService {
    @Transactional
    override fun create(guide: Guide): Guide {
        val notCreatedCategories = guide.categories.filter { it.id == null }

        val categories = guide.categories.filter { it.id != null }.toMutableList()

        if (notCreatedCategories.isNotEmpty())
            categories.addAll(categoryService.saveAll(notCreatedCategories))

        val databaseCategories = categoryService.getByIds(categories.map { it.id!! })

        return repository.save(GuideEntity.from(guide, databaseCategories)).toEntity()

    }

    override fun findAll(pegeable: Pageable): Page<Guide> {
        return repository.findAll(pegeable).map { it.toEntity() }
    }

    override fun getGuide(slug: String): Guide =
        repository.findFirstBySlug(slug).toEntity()
}