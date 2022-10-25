package com.organizee.domain.exceptions

object ErrorCodes {


    fun USER_ALREADY_EXISTS_EXCEPTION() =
        BussinessException("O usuário já está cadastrado em nosso sistema")

    fun USERNAME_ALREADY_EXISTS_EXCEPTION(params: List<String>) =
        BussinessException("Já existe um usuário com o username @${params[0]}")

    fun GUIDE_NOT_FOUND(params: List<String>) =
        NotFoundException("Guia com slug ${params[0]} não foi encontrado")

    fun USER_NOT_FOUND(params: List<String>) =
        NotFoundException("Usuário @${params[0]} não foi encontrado")

    fun COMMENT_NOT_FOUND() =
        NotFoundException("Comentário não foi encontrado")

    fun USER_ID_NOT_FOUND() =
        NotFoundException("Usuário não foi encontrado")

    fun GUIDE_ALREADY_SAVED(params: List<String>) =
        BussinessException("Você já salvou o guia ${params[0]}")

    fun GUIDE_ALREADY_LIKED(params: List<String>) =
        BussinessException("Você já curtiu o guia ${params[0]}")

    fun SAVED_GUIDE_NOT_FOUND() = NotFoundException("Guia salvo não foi encontrado")

    fun LIKE_NOT_FOUND() = NotFoundException("Curtida não encontrada")

    fun CANNOT_COMMENT() = BussinessException("Comentário não pode ter comentários associados")

    fun NOT_USER_OWNER() = BussinessException("Usuárion não tem permissão para realizar essa ação")

    fun CANNOT_FOLLOW() = BussinessException("Usuário não pode ser seguido")

    fun USER_NOT_FOLLOWED() = BussinessException("Usuário não segue usuário")

    fun FOLLOW_NOT_FOUND() = NotFoundException("Usuário não segue usuário")

}