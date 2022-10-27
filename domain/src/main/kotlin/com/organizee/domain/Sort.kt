package com.organizee.domain

enum class Sort {
    ASC,
    DESC;

    companion object {
        fun from(sortDirection: String) = when (sortDirection.uppercase()) {
            "ASC" -> ASC
            "DESC" -> DESC
            else -> ASC
        }
    }
}