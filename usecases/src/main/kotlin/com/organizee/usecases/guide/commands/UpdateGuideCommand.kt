package com.organizee.usecases.guide.commands

data class UpdateGuideCommand(
    val slug: String,
    val userId: String,
    val imgUrl: String? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val content: String? = null,
    val isPrivate: Boolean? = null,
    val categories: List<Long>? = null,
    val topics: List<String>? = null
)