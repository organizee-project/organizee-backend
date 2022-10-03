package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.CommentService
import com.organizee.domain.Page
import com.organizee.domain.guide.Comment
import com.organizee.usecases.guide.GetCommentsUseCase
import com.organizee.usecases.guide.commands.GetCommentsCommand
import org.springframework.stereotype.Service

@Service
class GetCommentsUsecaseImpl(
    private val commentService: CommentService
) : GetCommentsUseCase {
    override fun execute(input: GetCommentsCommand): Page<Comment> {
        return commentService.getComments(input.slug, input.page, input.size)
    }
}