package com.organizee.boundaries.db.entities

import org.springframework.data.domain.Sort

data class FilterGuide(
    val category: String,
    val page: Int,
    val size: Int,
    val sort: Sort.Direction,
    val sortBy: FilterSortBy
) {
    companion object {
        fun create(
            category: String,
            page: Int,
            size: Int,
            sort: com.organizee.domain.Sort,
            sortBy: String
        ): FilterGuide {

            val direction = when (sort) {
                com.organizee.domain.Sort.ASC -> Sort.Direction.ASC
                com.organizee.domain.Sort.DESC -> Sort.Direction.DESC
            }

            return FilterGuide(
                category = category,
                page = page,
                size = size,
                sort = direction,
                sortBy = FilterSortBy.from(sortBy)
            )
        }
    }
}

enum class FilterSortBy {
    POPULARITY,
    DATE;

    companion object {
        fun from(sortBy: String) =
            when (sortBy) {
                "popularity" -> POPULARITY
                "date" -> DATE
                else -> POPULARITY
            }
    }

}