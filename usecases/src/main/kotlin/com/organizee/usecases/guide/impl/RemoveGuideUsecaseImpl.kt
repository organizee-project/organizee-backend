package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.search.SearchService
import com.organizee.usecases.guide.RemoveGuideUseCase
import com.organizee.usecases.guide.commands.DeleteGuideCommand
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

    override fun execute(input: DeleteGuideCommand) {
        logger.info("Removing guide | slug=$input")
        val guide = guideService.getGuide(input.slug)

        if (guide.user?.id != input.userId)
            throw Exception("Guide is not owned by user")

        searchService.delete(guide)
        guideService.removeGuide(input.slug)
    }
}