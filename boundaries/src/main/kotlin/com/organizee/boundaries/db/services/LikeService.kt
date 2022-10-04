package com.organizee.boundaries.db.services

import com.organizee.domain.Page
import com.organizee.domain.guide.Like

interface LikeService {
    fun add(like: Like): Like
    fun getLikes(slug: String, page: Int, size: Int): Page<Like>
}