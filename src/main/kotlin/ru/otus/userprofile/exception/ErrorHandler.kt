package ru.otus.userprofile.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import ru.otus.userprofile.dto.Error

@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler
    fun handleBaseException(ex: ApplicationException) : ResponseEntity<Error> =
        ResponseEntity(ex.error, HttpStatusCode.valueOf(ex.error.code))

    @ExceptionHandler
    fun handleBaseException(ex: RuntimeException) : ResponseEntity<Error> =
        ResponseEntity(Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.message ?: ex.localizedMessage), HttpStatus.INTERNAL_SERVER_ERROR)
}