package com.organizee.domain.events

import com.organizee.domain.guide.Guide

data class AddGuideEvent(val guide: Guide) : DomainEvent<Guide>(
    entityId = guide.slug, entity = guide
)
