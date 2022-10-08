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
    override fun execute(input: NewLikeCommand): Like {
        val user = userService.findByUserIdOrThrow(input.userId)
        val guide = guideService.getGuideBySlugOrThrow(input.slug)

        val like = likeService.findLikeByUsernameAndSlug(user.username, guide.slug)

        if (like != null)
            throw IllegalStateException("Guide already liked")

        val newLike = Like(user, guide)

        return likeService.add(newLike)
    }
}