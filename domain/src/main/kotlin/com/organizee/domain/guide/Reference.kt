package com.organizee.domain.guide

import java.util.*

data class Reference(
    val id: UUID,
    val title: String,
    val url: String
) {
    companion object {
        fun create(title: String, url: String) = Reference(UUID.randomUUID(), title, url)
    }

}
