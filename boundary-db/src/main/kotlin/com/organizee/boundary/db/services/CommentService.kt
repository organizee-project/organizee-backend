package com.organizee.boundary.db.services

import com.organizee.guide.Comment

interface CommentService {
    fun create(comment: Comment, guideSlug: String): Comment
}