package com.organizee.adapter.events

import com.organizee.domain.events.LikeEvent
import com.organizee.domain.events.SaveEvent
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ActivityListerner {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    @EventListener
    fun likeEventLister(event: LikeEvent) {
        logger.info("Like event received: $event")
    }

    @EventListener
    fun saveEventLister(event: SaveEvent) {
        logger.info("Save event received: $event")
    }
}