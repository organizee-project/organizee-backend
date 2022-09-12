package com.organizee.search.entities

data class Fields(
    val title: String? = null,
    val slug: String? = null,
    val subtitle: String? = null,
    val content: String? = null,
    val categories: List<String> = emptyList(),
    val topics: List<String> = emptyList(),
)