package com.organizee.usecases.guide.commands

data class GetPublicGuidesCommand(
    val page: Int,
    val size: Int
)