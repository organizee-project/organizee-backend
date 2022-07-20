package com.organizee

import java.time.LocalDate

data class Guide(
    val title: String,
    val subtitle: String,
    val content: String,
    val createdAt: LocalDate
) {
    companion object {
        fun create(title: String, subtitle: String, content: String) = Guide(
            title = title,
            subtitle = subtitle,
            content = content,
            createdAt = LocalDate.now()
        )
    }
}
