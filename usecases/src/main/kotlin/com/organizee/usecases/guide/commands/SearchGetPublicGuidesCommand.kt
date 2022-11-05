package com.organizee.usecases.guide.commands

data class SearchGetPublicGuidesCommand private constructor(
    val page: Int,
    val size: Int,
    val filter: String
) {
    companion object {
        fun create(filter: String, page: Int, size: Int) =
            SearchGetPublicGuidesCommand(
                filter = filter,
                page = page,
                size = size
            )
    }
}