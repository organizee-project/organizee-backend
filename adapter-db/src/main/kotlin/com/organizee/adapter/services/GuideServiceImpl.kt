package com.organizee.adapter.services

import com.organizee.Guide
import com.organizee.boundary.db.entities.GuideEntity
import com.organizee.boundary.db.repositories.GuideRepository
import com.organizee.boundary.db.services.GuideService
import org.springframework.stereotype.Service

@Service
class GuideServiceImpl(private val repository: GuideRepository) : GuideService {
    override fun create(newGuide: Guide) =
        repository.save(GuideEntity.from(newGuide)).toEntity()
}