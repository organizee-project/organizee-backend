package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.GetPublicGuidesUseCase
import com.organizee.usecases.guide.commands.GetPublicGuidesCommand
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class GetPublicGuidesUsecaseImpl(
    private val guideService: GuideService
) : GetPublicGuidesUseCase {
    override fun execute(input: GetPublicGuidesCommand): Page<Guide> {
        val guides = guideService.findAll(PageRequest.of(input.page, input.size))

        return Page.of(guides)
    }
}