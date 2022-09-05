package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.entities.FilterGuide
import com.organizee.domain.Page
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.GetPublicGuidesUseCase
import com.organizee.usecases.guide.commands.GetPublicGuidesCommand
import org.springframework.stereotype.Service

@Service
class GetPublicGuidesUsecaseImpl(
    private val guideService: GuideService
) : GetPublicGuidesUseCase {
    override fun execute(input: GetPublicGuidesCommand): Page<Guide> {

        val filter = FilterGuide.create(
            category = input.category,
            page = input.page,
            size = input.size,
            sort = input.sortDirection,
            sortBy = input.sortBy
        )

        val guides = guideService.findAllFilteredBy(
            filter
        )

        return Page.of(guides)
    }
}