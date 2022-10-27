package com.organizee.domain.guide

import java.util.*

data class Reference(
    val id: UUID,
    val url: String
) {
    companion object {
        fun create(url: String) = Reference(UUID.randomUUID(), url)
    }

}
