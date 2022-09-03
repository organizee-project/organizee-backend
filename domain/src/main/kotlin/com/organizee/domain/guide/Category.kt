package com.organizee.domain.guide

import com.organizee.domain.formatter.toSlug

data class Category(
    val id: Long? = null,
    val name: String,
    val slug: String
) {
    companion object {
        fun create(name: String) = Category(null, name, name.toSlug())
    }
}