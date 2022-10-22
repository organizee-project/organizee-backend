package com.organizee.web.exceptions

import com.organizee.domain.exceptions.BussinessException
import com.organizee.domain.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(BussinessException::class)
    fun handleBussinessException(
        ex: BussinessException
    ): ResponseEntity<ErrorDetails> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorDetails(HttpStatus.BAD_REQUEST, ex.message))
    }


    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(
        ex: NotFoundException
    ): ResponseEntity<ErrorDetails> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorDetails(HttpStatus.NOT_FOUND, ex.message))
    }


    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<ErrorDetails> {
        val errorList = ex
            .bindingResult
            .fieldErrors
        val errorDetails =
            ErrorDetails(HttpStatus.BAD_REQUEST, "Payload inválido", fieldErrors = errorList.map {
                ErrorField(field = it.field, error = it.defaultMessage ?: "")
            })
        return ResponseEntity.badRequest().body(errorDetails)
    }
}


data class ErrorDetails(
    val status: HttpStatus,
    val message: String,
    val fieldErrors: List<ErrorField>? = null
)

data class ErrorField(
    val field: String,
    val error: String
)