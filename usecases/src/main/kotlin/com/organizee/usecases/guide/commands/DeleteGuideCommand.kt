package com.organizee.usecases.guide.commands

data class DeleteGuideCommand(
    val slug: String,
    val userId: String
)