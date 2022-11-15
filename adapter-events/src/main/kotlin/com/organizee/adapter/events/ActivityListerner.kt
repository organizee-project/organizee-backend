package com.organizee.adapter.events

import com.organizee.domain.events.*
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


    @EventListener
    fun commentEventListener(event: CommentEvent) {
        logger.info("comment event received: $event")

        addActivityUseCase.execute(
            NewActivityCommand(
                type = "COMMENT",
                date = event.date,
                userId = event.entity.user.id,
                referenceId = event.entity.guide.slug
            )
        )
    }


    @EventListener
    fun addGuideEventLister(event: AddGuideEvent) {
        logger.info("add guide event received: $event")

        addActivityUseCase.execute(
            NewActivityCommand(
                type = "ADD_GUIDE",
                date = event.date,
                userId = event.entity.user.id,
                referenceId = event.entity.slug
            )
        )
    }

    @EventListener
    fun followEventListener(event: FollowEvent) {
        logger.info("comment event received: $event")

        addActivityUseCase.execute(
            NewActivityCommand(
                type = "FOLLOW",
                date = event.date,
                userId = event.user.id,
                referenceId = event.entity.username
            )
        )
    }
}