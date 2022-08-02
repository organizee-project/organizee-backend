package com.organizee.guide.impl

import com.organizee.Page
import com.organizee.boundary.db.services.GuideService
import com.organizee.guide.GetPublicGuidesUseCase
import com.organizee.guide.Guide
import com.organizee.guide.commands.GetPublicGuidesCommand
import org.springframework.data.domain.PageRequest

class GetPublicGuidesUsecaseImpl(
    private val guideService: GuideService
) : GetPublicGuidesUseCase {
    override fun execute(input: GetPublicGuidesCommand): Page<Guide> {
        val guides = guideService.findAll(PageRequest.of(input.page, input.size))

        return Page.of(guides)
    }
}