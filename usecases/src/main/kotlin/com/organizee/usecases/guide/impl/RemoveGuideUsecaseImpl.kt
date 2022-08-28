package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.usecases.guide.RemoveGuideUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RemoveGuideUsecaseImpl(
    private val guideService: GuideService
) : RemoveGuideUseCase {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: String) {
        logger.info("Removing guide | slug=$input")
        guideService.removeGuide(input)
    }
}