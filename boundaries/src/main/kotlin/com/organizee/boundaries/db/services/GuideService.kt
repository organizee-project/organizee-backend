package com.organizee.boundaries.db.services

import com.organizee.boundaries.db.services.entities.FilterGuide
import com.organizee.domain.guide.Guide
import org.springframework.data.domain.Page

interface GuideService {
    fun findAllFilteredBy(filter: FilterGuide): Page<Guide>
    fun getGuide(slug: String): Guide
    fun removeGuide(slug: String)
    fun update(slug: String, updatedGuide: Guide): Guide
    fun save(guide: Guide): Guide
}


