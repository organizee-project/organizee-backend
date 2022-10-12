package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.CommentService
import com.organizee.domain.Page
import com.organizee.domain.guide.Comment
import com.organizee.usecases.guide.GetReferencedCommentsUseCase
import com.organizee.usecases.guide.commands.GetReferencedCommentsCommand
import org.springframework.stereotype.Service

@Service
class GetReferencedCommentsUseCaseImpl(
    private val commentService: CommentService
) : GetReferencedCommentsUseCase {
    override fun execute(input: GetReferencedCommentsCommand): Page<Comment> {
        return commentService.getCommentsByReferencedCommentId(
            input.commentId,
            input.page,
            input.size
        )
    }

}