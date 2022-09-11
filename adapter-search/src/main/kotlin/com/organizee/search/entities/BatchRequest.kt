package com.organizee.search.entities

import com.organizee.domain.guide.Guide

data class BatchRequest(
    val type: String,
    val id: String,
    val fields: Fields
) {
    companion object {
        fun createAdd(guide: Guide) = BatchRequest(
            type = "add",
            id = guide.slug,
            fields = Fields(
                title = guide.title,
                slug = guide.slug,
                subtitle = guide.subtitle,
                content = guide.content,
                categories = guide.categories.map { it.slug },
                topics = guide.topics,
            )
        )
    }
}