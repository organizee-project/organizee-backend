package com.organizee.domain.events

import com.organizee.domain.guide.Comment

data class CommentEvent(val comment: Comment) : DomainEvent<Comment>(
    entityId = comment.id.toString(), entity = comment
)
