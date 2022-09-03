package com.organizee.usecases.guide.commands

import com.organizee.usecases.category.commands.CategoryCommand

data class NewGuideCommand(
    val title: String,
    val subtitle: String,
    val content: String,
    val categories: List<CategoryCommand>,
    val isPrivate: Boolean
)