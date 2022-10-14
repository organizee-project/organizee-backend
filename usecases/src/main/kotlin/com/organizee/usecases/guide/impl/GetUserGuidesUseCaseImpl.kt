package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.GetUserGuidesUseCase
import com.organizee.usecases.guide.commands.GetUserGuidesCommand
import org.springframework.stereotype.Service

@Service
class GetUserGuidesUseCaseImpl(
    private val guideService: GuideService,
    private val userService: UserService
) : GetUserGuidesUseCase {
    override fun execute(input: GetUserGuidesCommand): Page<Guide> {
        val user = userService.findByUsernameOrThrow(input.username)

        val guides = when (user.id == input.userId) {
            true -> guideService.findAllByUser(user.username, input.page, input.size)
            else -> guideService.findAllPublicByUser(user.username, input.page, input.size)
        }

        return Page.of(guides)
    }
}