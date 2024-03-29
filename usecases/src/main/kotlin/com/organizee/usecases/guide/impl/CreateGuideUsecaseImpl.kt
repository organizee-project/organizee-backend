package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.CategoryService
import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.UserService
import com.organizee.boundaries.search.SearchService
import com.organizee.domain.events.AddGuideEvent
import com.organizee.domain.guide.Category
import com.organizee.domain.guide.Guide
import com.organizee.domain.guide.Reference
import com.organizee.domain.user.User
import com.organizee.shared.events.DomainEventPublisherService
import com.organizee.usecases.guide.CreateGuideUseCase
import com.organizee.usecases.guide.commands.NewGuideCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateGuideUsecaseImpl(
    private val guideService: GuideService,
    private val userService: UserService,
    private val categoryService: CategoryService,
    private val searchService: SearchService,
    private val eventPublisherService: DomainEventPublisherService<Guide>
) : CreateGuideUseCase {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(input: NewGuideCommand): Guide {
        logger.info("Creating new guide | input=$input")

        val categories = categoryService.getAllIdsIn(input.categories)

        val user = userService.findByUserIdOrThrow(input.userId)
        var guide = createGuide(input, categories, user)



        guide = guideService.save(guide, input.userId)

        if (!guide.isPrivate()) {
            searchService.persist(guide)
            eventPublisherService.publish(AddGuideEvent(guide))
        }

        return guide
    }

    private fun createGuide(
        input: NewGuideCommand,
        categories: List<Category>,
        user: User
    ) = Guide.create(
        title = input.title,
        subtitle = input.subtitle,
        content = input.content,
        isPrivate = input.isPrivate,
        categories = categories,
        references = input.references.map {
            Reference.create(
                url = it.url
            )
        },
        topics = input.topics,
        imgUrl = input.imgUrl,
        user = user
    )


}