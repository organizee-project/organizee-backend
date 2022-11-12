package com.organizee.usecases.activity.commands

import java.time.LocalDateTime

data class NewActivityCommand(
    val type: String,
    val date: LocalDateTime,
    val userId: String,
    val referenceId: String
)
