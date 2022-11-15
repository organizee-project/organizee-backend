package com.organizee.domain.events

import com.organizee.domain.user.User

data class FollowEvent(val user: User, val followedUser: User) : DomainEvent<User>(
    entityId = user.username, entity = followedUser
)
