package com.organizee.guide.commands

data class NewGuideCommand(
    val title: String,
    val subtitle: String,
    val content: String,
    val categories: List<Long>,
    val isPrivate: Boolean
)
