package com.organizee.domain.events

import com.organizee.domain.guide.Like

data class LikeEvent(val like: Like) : DomainEvent<Like>(
    entityId = like.id.toString(), entity = like
)
