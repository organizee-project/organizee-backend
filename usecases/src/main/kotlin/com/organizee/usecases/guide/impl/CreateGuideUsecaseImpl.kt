package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.CategoryService
import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.CreateGuideUseCase
import com.organizee.usecases.guide.commands.NewGuideCommand
import org.springframework.stereotype.Service

@Service
class CreateGuideUsecaseImpl(
    private val guideService: GuideService,
    private val categoryService: CategoryService
) : CreateGuideUseCase {
    override fun execute(input: NewGuideCommand): Guide {

        val categories = categoryService.getAllIdsIn(input.categories)

        val guide = Guide.create(
            title = input.title,
            subtitle = input.subtitle,
            content = input.content,
            isPrivate = input.isPrivate,
            categories = categories
        )

        return guideService.save(guide)
    }
}