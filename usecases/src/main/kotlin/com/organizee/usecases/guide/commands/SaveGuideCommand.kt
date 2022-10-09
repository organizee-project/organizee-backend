package com.organizee.usecases.guide.commands

data class SaveGuideCommand(
    val userId: String,
    val slug: String
)
