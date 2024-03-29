package com.organizee.adapter.db.repositories

import com.organizee.adapter.db.entities.GuideEntity
import com.organizee.boundaries.db.entities.FilterGuide
import com.organizee.boundaries.db.entities.FilterGuideFollowing
import org.springframework.data.domain.Page

interface GuideCustomRepository {
    fun getFilteredGuides(filter: FilterGuide): Page<GuideEntity>
    fun getFollowingFilteredGuides(filter: FilterGuideFollowing): Page<GuideEntity>
}