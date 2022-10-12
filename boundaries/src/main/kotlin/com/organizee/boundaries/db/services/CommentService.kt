package com.organizee.boundaries.db.services

import com.organizee.domain.Page
import com.organizee.domain.guide.Comment
import java.util.*

interface CommentService {
    fun getCommentByIdOrThrow(id: UUID): Comment
    fun create(comment: Comment): Comment
    fun getComments(slug: String, page: Int, size: Int): Page<Comment>
    fun getCommentsByReferencedCommentId(id: UUID, page: Int, size: Int): Page<Comment>
    fun deleteById(id: UUID)
}