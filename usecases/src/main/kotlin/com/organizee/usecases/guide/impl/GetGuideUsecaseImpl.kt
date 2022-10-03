package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.usecases.guide.GetGuideUseCase
import org.springframework.stereotype.Service

@Service
class GetGuideUsecaseImpl(private val guideService: GuideService) : GetGuideUseCase {
    override fun execute(input: String) = guideService.getGuideBySlugOrThrow(input)
}