package com.organizee.domain.user

import com.organizee.domain.guide.Guide
import java.time.LocalDateTime
import java.util.*

data class Activity(
    val id: UUID,
    val date: LocalDateTime,
    val type: String,
    val guideReference: Guide
)
