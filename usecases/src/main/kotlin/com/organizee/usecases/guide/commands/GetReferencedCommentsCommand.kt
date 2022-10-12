package com.organizee.usecases.guide.commands

import java.util.*

data class GetReferencedCommentsCommand private constructor(
    val commentId: UUID,
    val page: Int,
    val size: Int
) {
    companion object {
        fun create(commentId: UUID, page: Int, size: Int) =
            GetReferencedCommentsCommand(
                commentId = commentId,
                page = page,
                size = size
            )
    }
}