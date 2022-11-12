package com.organizee.domain.activity

enum class ActivityType {
    LIKE,
    SAVE,
    OTHER;

    companion object {
        fun from(type: String) = kotlin.runCatching { valueOf(type) }.onFailure { OTHER }
    }
}

