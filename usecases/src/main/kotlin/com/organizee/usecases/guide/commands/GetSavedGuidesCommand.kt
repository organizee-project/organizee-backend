package com.organizee.usecases.guide.commands

data class GetSavedGuidesCommand private constructor(
    val userId: String,
    val page: Int,
    val size: Int
) {
    companion object {
        fun create(userId: String, page: Int, size: Int) =
            GetSavedGuidesCommand(
                userId = userId,
                page = page,
                size = size
            )
    }
}