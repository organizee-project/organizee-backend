package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.domain.guide.Category
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.UpdateGuideUseCase
import com.organizee.usecases.guide.commands.UpdateGuideCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UpdateGuideUsecaseImpl(
    private val guideService: GuideService
) : UpdateGuideUseCase {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: UpdateGuideCommand): Guide {
        logger.info("updating guide | slug=${input.slug}")

        val guide = guideService.getGuide(input.slug)

        return guideService.update(input.slug, getUpdatedGuide(guide, input))
    }

    private fun getUpdatedGuide(guide: Guide, input: UpdateGuideCommand) = guide.update(
        title = input.title,
        subtitle = input.subtitle,
        content = input.content,
        isPrivate = input.isPrivate,
        categories = input.categories?.map { category ->
            Category.create(
                id = category.id,
                name = category.name
            )
        }
    )

}