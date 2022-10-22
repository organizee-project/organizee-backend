package com.organizee.usecases.files.commands

import java.io.ByteArrayInputStream

data class AddFileCommand(
    val name: String,
    val stream: ByteArrayInputStream,
    val size: Long,
    val type: String
)
