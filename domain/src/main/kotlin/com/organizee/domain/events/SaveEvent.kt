package com.organizee.domain.events

import com.organizee.domain.guide.Saved

data class SaveEvent(val saved: Saved) : DomainEvent<Saved>(
    entityId = saved.id.toString(), entity = saved
)
