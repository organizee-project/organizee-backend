package com.organizee.usecases.guide.commands

import com.organizee.domain.Sort

data class GetPublicGuidesCommand private constructor(
    val category: String,
    val sortBy: String,
    val sortDirection: Sort = Sort.ASC,
    val page: Int,
    val size: Int
) {
    companion object {
        fun create(category: String, sortBy: String, sortDirection: String, page: Int, size: Int) =
            GetPublicGuidesCommand(
                category = category,
                sortBy = sortBy,
                sortDirection = Sort.from(sortDirection),
                page = page,
                size = size
            )
    }
}