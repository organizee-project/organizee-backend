package com.organizee.guide.impl

import com.organizee.boundary.db.services.CommentService
import com.organizee.guide.Comment
import com.organizee.guide.CreateCommentUseCase
import com.organizee.guide.commands.NewCommentCommand

class CreateCommentUseCaseImpl(private val commentService: CommentService) : CreateCommentUseCase {
    override fun execute(input: NewCommentCommand) =
        commentService.create(Comment.create(message = input.message), input.slug)
}