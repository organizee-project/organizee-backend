package com.organizee.usecases.guide.commands

data class GetSavedGuidesCommand private constructor(
    val username: String,
    val page: Int,
    val size: Int
) {
    companion object {
        fun create(username: String, page: Int, size: Int) =
            GetSavedGuidesCommand(
                username = username,
                page = page,
                size = size
            )
    }
}