package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.usecases.guide.CheckGuideInteractionsUseCase
import com.organizee.usecases.guide.commands.CheckGuideInteractionsCommand
import com.organizee.usecases.guide.responses.CheckGuideInteractionsOutput
import org.springframework.stereotype.Service

@Service
class CheckGuideInteractionsUseCaseImpl(private val guideService: GuideService) :
    CheckGuideInteractionsUseCase {
    override fun execute(input: CheckGuideInteractionsCommand): CheckGuideInteractionsOutput {
        guideService.getGuideBySlugOrThrow(input.slug)

        val saved = guideService.isSaved(input.userId, input.slug)
        val liked = guideService.isLiked(input.userId, input.slug)

        return CheckGuideInteractionsOutput(
            liked = liked, saved = saved
        )

    }
}