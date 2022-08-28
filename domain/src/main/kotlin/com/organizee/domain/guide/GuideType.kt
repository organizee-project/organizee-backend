package com.organizee.domain.guide

enum class GuideType {
    PRIVATE,
    PUBLIC;

    companion object {
        fun from(isPrivate: Boolean) = when (isPrivate) {
            true -> PRIVATE
            else -> PUBLIC
        }
    }
}