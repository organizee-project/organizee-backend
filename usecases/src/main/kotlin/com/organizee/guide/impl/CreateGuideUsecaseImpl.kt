package com.organizee.guide.impl

import com.organizee.boundary.db.services.GuideService
import com.organizee.guide.Category
import com.organizee.guide.CreateGuideUseCase
import com.organizee.guide.Guide
import com.organizee.guide.commands.NewGuideCommand

class CreateGuideUsecaseImpl(private val guideService: GuideService) : CreateGuideUseCase {
    override fun execute(input: NewGuideCommand): Guide {
        val guide = Guide.create(
            title = input.title,
            subtitle = input.subtitle,
            content = input.content,
            isPrivate = input.isPrivate,
            categories = input.categories.map {
                Category.create(it.id, it.name)
            }
        )
        return guideService.create(guide)

    }
}