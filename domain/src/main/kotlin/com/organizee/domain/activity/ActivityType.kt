package com.organizee.domain.activity

enum class ActivityType {
    LIKE,
    SAVE,
    COMMENT,
    ADD_GUIDE,
    FOLLOW,
    OTHER;

    companion object {
        fun from(type: String) = kotlin.runCatching { valueOf(type) }.onFailure { OTHER }
    }
}

