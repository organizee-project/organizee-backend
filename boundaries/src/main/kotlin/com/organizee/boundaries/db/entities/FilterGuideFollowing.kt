package com.organizee.boundaries.db.entities

import org.springframework.data.domain.Sort

data class FilterGuideFollowing(
    val userId: String,
    val page: Int,
    val size: Int,
    val sort: Sort.Direction,
    val sortBy: FilterSortBy
) {
    companion object {
        fun create(
            userId: String,
            page: Int,
            size: Int,
            sort: com.organizee.domain.Sort,
            sortBy: String
        ): FilterGuideFollowing {

            val direction = when (sort) {
                com.organizee.domain.Sort.ASC -> Sort.Direction.ASC
                com.organizee.domain.Sort.DESC -> Sort.Direction.DESC
            }

            return FilterGuideFollowing(
                userId = userId,
                page = page,
                size = size,
                sort = direction,
                sortBy = FilterSortBy.from(sortBy)
            )
        }
    }
}
