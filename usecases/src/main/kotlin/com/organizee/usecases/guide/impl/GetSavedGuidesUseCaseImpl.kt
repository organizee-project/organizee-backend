package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.GetSavedGuidesUseCase
import com.organizee.usecases.guide.commands.GetSavedGuidesCommand
import org.springframework.stereotype.Service

@Service
class GetSavedGuidesUseCaseImpl(
    private val guideService: GuideService
) : GetSavedGuidesUseCase {

    override fun execute(input: GetSavedGuidesCommand): Page<Guide> {
        val guides = guideService.findSaved(
            input.username, input.page, input.size
        )

        return Page.of(guides)
    }
}