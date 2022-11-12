package com.organizee.shared.events

import com.organizee.domain.events.DomainEvent

interface DomainEventPublisherService<T> {

    fun publish(event: DomainEvent<T>)
}