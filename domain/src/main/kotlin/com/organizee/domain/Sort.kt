package com.organizee.domain

enum class Sort {
    ASC,
    DESC;

    companion object {
        fun from(sortDirection: String) = when (sortDirection) {
            "asc" -> ASC
            "desc" -> DESC
            else -> ASC
        }
    }
}