package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.entities.FilterGuideFollowing
import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.GetFollowingGuidesUseCase
import com.organizee.usecases.guide.commands.GetFollowingGuidesCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GetFollowingGuidesUsecaseImpl(
    private val guideService: GuideService
) : GetFollowingGuidesUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: GetFollowingGuidesCommand): Page<Guide> {
        logger.info("Searching for following guides | input: $input")

        val filter = FilterGuideFollowing.create(
            userId = input.userId,
            page = input.page,
            size = input.size,
            sort = input.sortDirection,
            sortBy = input.sortBy
        )

        val guides = guideService.findAllFromFollwingFilteredBy(filter)

        return Page.of(guides)
    }
}