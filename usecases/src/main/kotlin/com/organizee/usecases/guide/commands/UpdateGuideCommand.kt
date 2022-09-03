package com.organizee.usecases.guide.commands

import com.organizee.usecases.category.commands.CategoryCommand

data class UpdateGuideCommand(
    val slug: String,
    val title: String? = null,
    val subtitle: String? = null,
    val content: String? = null,
    val isPrivate: Boolean? = null,
    val categories: List<CategoryCommand>? = null
)