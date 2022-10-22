package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.SavedService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.usecases.guide.SaveGuideUseCase
import com.organizee.usecases.guide.commands.SaveGuideCommand
import org.springframework.stereotype.Service

@Service
class SaveGuideUseCase(
    private val userService: UserService,
    private val savedService: SavedService,
    private val guideService: GuideService,
) : SaveGuideUseCase {
    override fun execute(input: SaveGuideCommand) {
        val user = userService.findByUserIdOrThrow(input.userId)
        val guide = guideService.getGuideBySlugOrThrow(input.slug)

        val saved = savedService.findSaved(input.userId, input.slug)

        if (saved != null)
            throw ErrorCodes.GUIDE_ALREADY_SAVED(listOf(guide.title))

        guideService.guideSavedByUser(input.slug, input.userId)

    }
}