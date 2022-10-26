package com.organizee.usecases.guide.commands

data class NewGuideCommand(
    val title: String,
    val imgUrl: String,
    val subtitle: String,
    val content: String,
    val categories: List<Long>,
    val topics: List<String> = emptyList(),
    val userId: String,
    val references: List<NewReferenceCommand> = emptyList(),
    val isPrivate: Boolean
)