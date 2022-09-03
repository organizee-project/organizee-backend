package com.organizee.domain.guide

import com.organizee.domain.formatter.toSlug

data class Category(
    val id: Long? = null,
    val name: String,
    val slug: String
) {
    companion object {
        fun create(id: Long?, name: String) = Category(id, name, name.toSlug())

        fun create(name: String) = Category(null, name, name.toSlug())
    }
}