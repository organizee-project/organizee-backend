package com.organizee.boundaries.db.services

import com.organizee.domain.guide.Like

interface LikeService {
    fun add(like: Like): Like
}