package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.SavedService
import com.organizee.usecases.guide.SaveGuideUseCase
import com.organizee.usecases.guide.commands.SaveGuideCommand
import org.springframework.stereotype.Service

@Service
class SaveGuideUseCase(
    private val savedService: SavedService,
    private val guideService: GuideService,
) : SaveGuideUseCase {
    override fun execute(input: SaveGuideCommand) {
        val saved = savedService.findSaved(input.userId, input.slug)

        if (saved != null)
            throw IllegalStateException("Guide already saved")

        guideService.guideSavedByUser(input.slug, input.userId)

    }
}