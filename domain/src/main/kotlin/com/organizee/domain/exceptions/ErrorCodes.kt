package com.organizee.domain.exceptions

object ErrorCodes {


    fun USER_ALREADY_EXISTS_EXCEPTION() =
        BussinessException("O usuário já está cadastrado em nosso sistema")

    fun USERNAME_ALREADY_EXISTS_EXCEPTION(params: List<String>) =
        BussinessException("Já existe um usuário com o username ${params[0]}")
}