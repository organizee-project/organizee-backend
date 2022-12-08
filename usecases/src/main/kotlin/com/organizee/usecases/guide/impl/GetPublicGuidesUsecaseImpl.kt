package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.entities.FilterGuide
import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.GetPublicGuidesUseCase
import com.organizee.usecases.guide.commands.GetPublicGuidesCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GetPublicGuidesUsecaseImpl(
    private val guideService: GuideService
) : GetPublicGuidesUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: GetPublicGuidesCommand): Page<Guide> {
        logger.info("Searching for public guides | input: $input")

        val filter = FilterGuide.create(
            category = input.category,
            page = input.page,
            size = input.size,
            sort = input.sortDirection,
            sortBy = input.sortBy
        )

        return try {
            val guides = guideService.findAllFilteredBy(filter)
            Page.of(guides)
        } catch (e: Exception) {
            Page.empty()
        }
    }
}