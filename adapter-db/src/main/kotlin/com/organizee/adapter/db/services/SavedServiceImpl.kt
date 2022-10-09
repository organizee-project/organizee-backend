package com.organizee.adapter.db.services

import com.organizee.adapter.db.repositories.SavedRepository
import com.organizee.boundaries.db.services.SavedService
import com.organizee.domain.guide.Saved
import org.springframework.stereotype.Service

@Service
class SavedServiceImpl(
    private val repository: SavedRepository
) : SavedService {
    override fun findSaved(userId: String, slug: String): Saved? {
        return repository.findFirstByUserIdAndGuideSlug(userId, slug)?.toEntity()
    }
}