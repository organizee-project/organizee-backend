package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.CommentService
import com.organizee.domain.guide.Comment
import com.organizee.usecases.guide.CreateCommentUseCase
import com.organizee.usecases.guide.commands.NewCommentCommand
import org.springframework.stereotype.Service

@Service
class CreateCommentUseCaseImpl(private val commentService: CommentService) : CreateCommentUseCase {
    override fun execute(input: NewCommentCommand) =
        commentService.create(Comment.create(message = input.message), input.slug)
}