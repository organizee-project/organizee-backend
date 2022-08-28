package com.organizee.usecases.guide.commands

data class NewGuideCommand(
    val title: String,
    val subtitle: String,
    val content: String,
    val categories: List<NewCategoryCommand>,
    val isPrivate: Boolean
)

data class NewCategoryCommand(
    val id: Long? = null,
    val name: String
)