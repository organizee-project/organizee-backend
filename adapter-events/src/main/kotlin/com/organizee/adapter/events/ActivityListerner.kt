package com.organizee.adapter.events

import com.organizee.domain.events.LikeEvent
import com.organizee.domain.events.SaveEvent
import com.organizee.usecases.activity.AddActivityUseCase
import com.organizee.usecases.activity.commands.NewActivityCommand
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ActivityListerner(
    private val addActivityUseCase: AddActivityUseCase
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @EventListener
    fun likeEventLister(event: LikeEvent) {
        logger.info("Like event received: $event")

        addActivityUseCase.execute(
            NewActivityCommand(
                type = "LIKE",
                date = event.date,
                userId = event.entity.user.id,
                referenceId = event.entity.guide.slug
            )
        )

    }

    @EventListener
    fun saveEventLister(event: SaveEvent) {
        logger.info("Save event received: $event")

        addActivityUseCase.execute(
            NewActivityCommand(
                type = "SAVE",
                date = event.date,
                userId = event.entity.user.id,
                referenceId = event.entity.guide.slug
            )
        )
    }
}