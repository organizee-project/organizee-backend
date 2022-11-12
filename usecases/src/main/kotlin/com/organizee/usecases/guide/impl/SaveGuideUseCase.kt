package com.organizee.usecases.guide.impl

import com.organizee.boundaries.db.services.GuideService
import com.organizee.boundaries.db.services.SavedService
import com.organizee.boundaries.db.services.UserService
import com.organizee.domain.events.SaveEvent
import com.organizee.domain.exceptions.ErrorCodes
import com.organizee.domain.guide.Saved
import com.organizee.shared.events.DomainEventPublisherService
import com.organizee.usecases.guide.SaveGuideUseCase
import com.organizee.usecases.guide.commands.SaveGuideCommand
import org.springframework.stereotype.Service

@Service
class SaveGuideUseCase(
    private val userService: UserService,
    private val savedService: SavedService,
    private val guideService: GuideService,
    private val eventPublisherService: DomainEventPublisherService<Saved>
) : SaveGuideUseCase {
    override fun execute(input: SaveGuideCommand) {
        userService.findByUserIdOrThrow(input.userId)
        val guide = guideService.getGuideBySlugOrThrow(input.slug)

        var saved = savedService.findSaved(input.userId, input.slug)

        if (saved != null)
            throw ErrorCodes.GUIDE_ALREADY_SAVED(listOf(guide.title))

        saved = guideService.guideSavedByUser(input.slug, input.userId)

        eventPublisherService.publish(SaveEvent(saved))
    }
}