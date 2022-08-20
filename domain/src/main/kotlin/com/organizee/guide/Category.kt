package com.organizee.guide

import com.organizee.boundary.shared.utils.toSlug

data class Category(
    val id: Long?,
    val name: String,
    val slug: String
) {
    companion object {
        fun create(id: Long?, name: String) = Category(id, name, name.toSlug())
    }
}