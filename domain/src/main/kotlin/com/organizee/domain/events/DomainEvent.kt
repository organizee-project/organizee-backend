package com.organizee.domain.events

import java.time.LocalDateTime
import java.util.*

sealed class DomainEvent<T>(
    val id: UUID = UUID.randomUUID(),
    val date: LocalDateTime = LocalDateTime.now(),

    val entityId: String,
    open val entity: T
)