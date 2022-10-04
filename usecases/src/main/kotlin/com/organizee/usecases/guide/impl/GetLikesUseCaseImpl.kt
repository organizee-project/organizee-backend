package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.LikeService
import com.organizee.domain.Page
import com.organizee.domain.guide.Like
import com.organizee.usecases.guide.GetLikesUseCase
import com.organizee.usecases.guide.commands.GetLikesCommand
import org.springframework.stereotype.Service

@Service
class GetLikesUseCaseImpl(
    private val likeService: LikeService
) : GetLikesUseCase {
    override fun execute(input: GetLikesCommand): Page<Like> {
        return likeService.getLikes(input.slug, input.page, input.size)
    }
}