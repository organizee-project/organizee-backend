package com.organizee.boundaries.db.services

import com.organizee.domain.guide.Guide
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GuideService {
    fun save(guide: Guide): Guide
    fun findAll(pegeable: Pageable): Page<Guide>
    fun getGuide(slug: String): Guide
    fun removeGuide(slug: String)
    fun update(slug: String, updatedGuide: Guide): Guide
}