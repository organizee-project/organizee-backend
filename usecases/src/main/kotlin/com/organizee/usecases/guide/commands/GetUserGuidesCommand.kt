package com.organizee.usecases.guide.commands

data class GetUserGuidesCommand private constructor(
    val userId: String? = null,
    val username: String,
    val page: Int,
    val size: Int
) {
    companion object {
        fun create(userId: String?, username: String, page: Int, size: Int) =
            GetUserGuidesCommand(
                userId = userId,
                username = username,
                page = page,
                size = size
            )
    }
}