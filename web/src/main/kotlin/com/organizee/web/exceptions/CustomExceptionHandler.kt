package com.organizee.web.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException
    ): ResponseEntity<ErrorDetails> {
        val errorList = ex
            .bindingResult
            .fieldErrors
        val errorDetails = ErrorDetails(HttpStatus.BAD_REQUEST, errorList.map {
            ErrorField(field = it.field, error = it.defaultMessage ?: "")
        })
        return ResponseEntity.badRequest().body(errorDetails)
    }
}

data class ErrorDetails(
    val status: HttpStatus,
    val errors: List<ErrorField>
)

data class ErrorField(
    val field: String,
    val error: String
)