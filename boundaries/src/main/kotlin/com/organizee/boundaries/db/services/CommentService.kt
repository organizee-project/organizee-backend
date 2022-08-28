package com.organizee.boundaries.db.services

import com.organizee.domain.guide.Comment

interface CommentService {
    fun create(comment: Comment, guideSlug: String): Comment
}