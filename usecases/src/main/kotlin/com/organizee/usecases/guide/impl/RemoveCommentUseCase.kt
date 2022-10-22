package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.CommentService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.usecases.guide.RemoveCommentUseCase
import com.organizee.usecases.guide.commands.RemoveCommentCommand
import org.springframework.stereotype.Service

@Service
class RemoveCommentUseCase(
    private val userService: UserService,
    private val commentService: CommentService
) : RemoveCommentUseCase {
    override fun execute(input: RemoveCommentCommand) {
        val user = userService.findByUserIdOrThrow(input.userId)

        val comment = commentService.getCommentByIdOrThrow(input.commentId)

        if (!comment.checkUser(user.id))
            throw ErrorCodes.NOT_USER_OWNER()

        commentService.deleteById(comment.id)
    }
}