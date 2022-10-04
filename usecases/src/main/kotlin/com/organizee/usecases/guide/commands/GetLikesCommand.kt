package com.organizee.usecases.guide.commands

data class GetLikesCommand private constructor(
    val slug: String,
    val page: Int,
    val size: Int
) {
    companion object {
        fun create(slug: String, page: Int, size: Int) =
            GetLikesCommand(
                slug = slug,
                page = page,
                size = size
            )
    }
}