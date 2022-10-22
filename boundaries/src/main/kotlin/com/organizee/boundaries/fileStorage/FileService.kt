package com.organizee.boundaries.fileStorage

import java.io.ByteArrayInputStream

interface FileService {
    fun addPublicFile(file: FileInput): String
}

data class FileInput(
    val name: String,
    val stream: ByteArrayInputStream,
    val size: Long,
    val type: String
)