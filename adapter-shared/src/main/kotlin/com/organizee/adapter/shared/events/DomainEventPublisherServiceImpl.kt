package com.organizee.adapter.shared.events

import com.organizee.domain.events.DomainEvent
import com.organizee.shared.events.DomainEventPublisherService
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class DomainEventPublisherServiceImpl<T>(
    private val appEventPublisher: ApplicationEventPublisher,
) : DomainEventPublisherService<T> {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun publish(event: DomainEvent<T>) {
        logger.info("Executing domain events")

        appEventPublisher.publishEvent(event)
    }
}