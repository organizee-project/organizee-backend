package com.organizee.boundary.db.services

import com.organizee.guide.Guide
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GuideService {
    fun create(guide: Guide): Guide
    fun findAll(pegeable: Pageable): Page<Guide>
    fun getGuide(slug: String): Guide
}