package com.organizee.adapter.services

import com.organizee.boundary.db.entities.GuideEntity
import com.organizee.boundary.db.repositories.GuideRepository
import com.organizee.boundary.db.services.GuideService
import com.organizee.guide.Guide
import org.springframework.stereotype.Service

@Service
class GuideServiceImpl(private val repository: GuideRepository) : GuideService {
    override fun create(guide: Guide) =
        repository.save(GuideEntity.from(guide)).toEntity()

    override fun getGuide(slug: String): Guide =
        repository.findFirstBySlug(slug).toEntity()
}