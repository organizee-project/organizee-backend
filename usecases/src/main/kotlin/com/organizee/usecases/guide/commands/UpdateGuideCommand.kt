package com.organizee.usecases.guide.commands

data class UpdateGuideCommand(
    val slug: String,
    val title: String? = null,
    val subtitle: String? = null,
    val content: String? = null,
    val isPrivate: Boolean? = null,
    val categories: List<Long>? = null
)