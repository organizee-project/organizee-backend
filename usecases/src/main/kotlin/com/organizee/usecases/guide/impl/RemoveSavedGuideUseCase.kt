package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.SavedService
import com.organizee.usecases.guide.RemoveSavedGuideUseCase
import com.organizee.usecases.guide.commands.SaveGuideCommand
import org.springframework.stereotype.Service

@Service
class RemoveSavedGuideUseCase(
    private val savedService: SavedService,
) : RemoveSavedGuideUseCase {
    override fun execute(input: SaveGuideCommand) {
        val saved = savedService.findSavedOrThrow(input.userId, input.slug)
        savedService.remove(saved)
    }
}