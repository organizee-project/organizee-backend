package com.organizee.domain.activity

import com.organizee.domain.user.User
import java.time.LocalDateTime
import java.util.*

data class Activity(
    val id: UUID = UUID.randomUUID(),
    val date: LocalDateTime,
    val type: ActivityType,
    val referenceId: String,
    val description: String,
    val user: User
)
