package com.organizee.usecases.guide.commands

data class CheckGuideInteractionsCommand(
    val userId: String,
    val slug: String
)
