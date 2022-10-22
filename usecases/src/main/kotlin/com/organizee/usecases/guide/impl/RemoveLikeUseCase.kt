package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.LikeService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.usecases.guide.RemoveLikeUseCase
import com.organizee.usecases.guide.commands.RemoveLikeCommand
import org.springframework.stereotype.Service

@Service
class RemoveLikeUseCase(
    private val userService: UserService,
    private val likeService: LikeService
) : RemoveLikeUseCase {
    override fun execute(input: RemoveLikeCommand) {
        val user = userService.findByUserIdOrThrow(input.userId)

        val like = likeService.findLikeByUsernameAndSlug(user.username, input.slug)
            ?: throw ErrorCodes.LIKE_NOT_FOUND()

        likeService.remove(like.id)
    }
}