package com.organizee.search.entities

data class Fields(
    val title: String,
    val slug: String,
    val subtitle: String,
    val content: String,
    val categories: List<String> = emptyList(),
    val topics: List<String> = emptyList(),
)