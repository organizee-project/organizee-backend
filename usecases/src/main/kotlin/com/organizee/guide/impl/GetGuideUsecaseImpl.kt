package com.organizee.guide.impl

import com.organizee.boundary.db.services.GuideService
import com.organizee.guide.GetGuideUseCase

class GetGuideUsecaseImpl(private val guideService: GuideService) : GetGuideUseCase {
    override fun execute(input: String) = guideService.getGuide(input)
}