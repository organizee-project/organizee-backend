package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.CommentService
import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.guide.Comment
import com.organizee.usecases.guide.AddCommentUseCase
import com.organizee.usecases.guide.commands.NewCommentCommand
import org.springframework.stereotype.Service

@Service
class AddCommentUseCase(
    private val userService: UserService,
    private val guideService: GuideService,
    private val commentService: CommentService
) : AddCommentUseCase {
    override fun execute(input: NewCommentCommand): Comment {

        val user = userService.findByUserIdOrThrow(input.userId)
        val guide = guideService.getGuideBySlugOrThrow(input.slug)

        val comment = Comment.create(
            message = input.message,
            user = user,
            guide = guide,
            referencedComment = input.referencedComment
        )

        comment.referencedComment?.also {
            val referenced = commentService.getCommentByIdOrThrow(it)
            if (referenced.hasReferencedComment())
                throw IllegalStateException("Comment already referenced")

        }

        return commentService.create(comment)
    }
}