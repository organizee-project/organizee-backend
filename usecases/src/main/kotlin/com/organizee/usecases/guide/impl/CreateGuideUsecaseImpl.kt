package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.guide.Category
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.CreateGuideUseCase
import com.organizee.usecases.guide.commands.NewGuideCommand
import org.springframework.stereotype.Service

@Service
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
        return guideService.save(guide)

    }
}