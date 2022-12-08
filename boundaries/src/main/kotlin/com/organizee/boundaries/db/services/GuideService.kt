package com.organizee.boundaries.db.services

import com.organizee.boundaries.db.entities.FilterGuide
import com.organizee.boundaries.db.entities.FilterGuideFollowing
import com.organizee.domain.guide.Guide
import com.organizee.domain.guide.Saved
import org.springframework.data.domain.Page

interface GuideService {
    fun findAllByUser(username: String, page: Int, size: Int): Page<Guide>
    fun findAllPublicByUser(username: String, page: Int, size: Int): Page<Guide>
    fun findSaved(username: String, page: Int, size: Int): Page<Guide>
    fun isSaved(userId: String, slug: String): Boolean
    fun findAllFilteredBy(filter: FilterGuide): Page<Guide>
    fun getGuideBySlugOrThrow(slug: String): Guide
    fun getAllPublicByUserId(userId: String): List<Guide>
    fun removeGuide(slug: String)
    fun update(slug: String, updatedGuide: Guide): Guide
    fun save(guide: Guide, userId: String): Guide
    fun getLikedByUser(username: String, page: Int, size: Int): Page<Guide>
    fun guideSavedByUser(slug: String, userId: String): Saved
    fun isLiked(userId: String, slug: String): Boolean
    fun findAllFromFollwingFilteredBy(filter: FilterGuideFollowing): Page<Guide>

}


