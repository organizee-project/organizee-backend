package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.GetLikesByUserUseCase
import com.organizee.usecases.guide.commands.GetLikesByUserCommand
import org.springframework.stereotype.Service

@Service
class GetLikesByUserUseCaseImpl(
    private val guideService: GuideService,
    private val userService: UserService
) : GetLikesByUserUseCase {

    override fun execute(input: GetLikesByUserCommand): Page<Guide> {

        val user = userService.findByUsernameOrThrow(input.username)

        val guides = guideService.getLikedByUser(user.username, input.page, input.size)

        return Page.of(guides)
    }
}