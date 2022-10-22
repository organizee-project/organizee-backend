package com.organizee.domain.file

import java.util.*

data class File private constructor(
    val name: String
) {
    companion object {
        fun valueOf(
            extension: String
        ) = File(
            name = getName(extension)
        )

        private fun getName(extension: String) = "${UUID.randomUUID()}.${extension.split("/")[1]}"
    }
}