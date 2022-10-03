package com.organizee.boundaries.db.services

import com.organizee.domain.Page
import com.organizee.domain.guide.Comment

interface CommentService {
    fun create(comment: Comment): Comment
    fun getComments(slug: String, page: Int, size: Int): Page<Comment>
}