package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.CategoryService
import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.search.SearchService
import com.organizee.domain.guide.Guide
import com.organizee.usecases.guide.CreateGuideUseCase
import com.organizee.usecases.guide.commands.NewGuideCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateGuideUsecaseImpl(
    private val guideService: GuideService,
    private val categoryService: CategoryService,
    private val searchService: SearchService
) : CreateGuideUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(CreateGuideUseCase::class.java)
    }

    override fun execute(input: NewGuideCommand): Guide {
        logger.info("Creating new guide | input=$input")

        val categories = categoryService.getAllIdsIn(input.categories)

        var guide = Guide.create(
            title = input.title,
            subtitle = input.subtitle,
            content = input.content,
            isPrivate = input.isPrivate,
            categories = categories,
            topics = input.topics
        )

        searchService.persist(guide)

        guide = guideService.save(guide)

        return guide
    }


}