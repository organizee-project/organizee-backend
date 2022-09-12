package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.search.SearchService
import com.organizee.usecases.guide.RemoveGuideUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RemoveGuideUsecaseImpl(
    private val guideService: GuideService,
    private val searchService: SearchService
) : RemoveGuideUseCase {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: String) {
        logger.info("Removing guide | slug=$input")
        val guide = guideService.getGuide(input)
        searchService.delete(guide)
        guideService.removeGuide(input)
    }
}