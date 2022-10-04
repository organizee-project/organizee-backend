package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.LikeService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.guide.Like
import com.organizee.usecases.guide.AddLikeUseCase
import com.organizee.usecases.guide.commands.NewLikeCommand
import org.springframework.stereotype.Service

@Service
class AddLikeUseCase(
    private val userService: UserService,
    private val guideService: GuideService,
    private val likeService: LikeService
) : AddLikeUseCase {
    /* override fun execute(input: NewCommentCommand): Comment {


        val comment = Comment.create(
            message = input.message,
            user = user,
            guide = guide
        )

        val createdComment = commentService.create(comment)

        return createdComment
    } */
    override fun execute(input: NewLikeCommand): Like {
        val user = userService.findByUserIdOrThrow(input.userId)
        val guide = guideService.getGuideBySlugOrThrow(input.slug)

        val newLike = Like(user, guide)

        return likeService.add(newLike)
    }
}