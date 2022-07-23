package com.organizee.adapter.services

import com.organizee.boundary.db.entities.GuideEntity
import com.organizee.boundary.db.repositories.CategoryRepository
import com.organizee.boundary.db.repositories.GuideRepository
import com.organizee.boundary.db.services.GuideService
import com.organizee.guide.Guide
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class GuideServiceImpl(
    private val repository: GuideRepository,
    private val categoryRepository: CategoryRepository
) : GuideService {
    @Transactional
    override fun create(guide: Guide, categories: List<Long>): Guide {

        val categories = categoryRepository.findByIdIn(categories)

        return repository.save(GuideEntity.from(guide, categories)).toEntity()

    }

    override fun getGuide(slug: String): Guide =
        repository.findFirstBySlug(slug).toEntity()
}