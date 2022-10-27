package com.organizee.usecases.user.commands

data class GetPerfilCommand(
    val userId: String? = null,
    val userName: String
)
