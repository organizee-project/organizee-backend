package com.organizee.boundaries.db.services

import com.organizee.domain.Page
import com.organizee.domain.guide.Like
import java.util.*

interface LikeService {
    fun findLikeByUsernameAndSlug(username: String, slug: String): Like?
    fun add(like: Like): Like
    fun getLikes(slug: String, page: Int, size: Int): Page<Like>
    fun remove(id: UUID)
}