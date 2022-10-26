package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.CategoryService
import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.search.SearchService
import com.organizee.domain.guide.Category
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.UpdateGuideUseCase
import com.organizee.usecases.guide.commands.UpdateGuideCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UpdateGuideUsecaseImpl(
    private val guideService: GuideService,
    private val categoryService: CategoryService,
    private val searchService: SearchService
) : UpdateGuideUseCase {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: UpdateGuideCommand): Guide {
        logger.info("updating guide | slug=${input.slug}")

        val guide = guideService.getGuideBySlugOrThrow(input.slug)

        if (guide.user?.id != input.userId)
            throw Exception("Guide is not owned by user")

        val categories = input.categories?.let {
            categoryService.getAllIdsIn(input.categories)
        }

        val updatedGuide =
            guideService.update(input.slug, getUpdatedGuide(guide, input, categories))

        searchService.update(updatedGuide, guide)

        return updatedGuide
    }

    private fun getUpdatedGuide(
        guide: Guide,
        input: UpdateGuideCommand,
        categories: List<Category>?
    ) = guide.update(
        title = input.title,
        subtitle = input.subtitle,
        content = input.content,
        isPrivate = input.isPrivate,
        categories = categories,
        topics = input.topics,
        imgUrl = input.imgUrl
    )

}