package com.organizee.web.controllers.user.json

import com.organizee.domain.activity.Activity
import java.time.LocalDateTime
import java.util.*

data class ActivitiesResponse(
    val id: UUID,
    val date: LocalDateTime,
    val type: String,
    val referenceId: String
) {
    companion object {
        fun fromEntity(entity: Activity) = ActivitiesResponse(
            id = entity.id,
            date = entity.date,
            type = entity.type.name,
            referenceId = entity.referenceId
        )
    }
}