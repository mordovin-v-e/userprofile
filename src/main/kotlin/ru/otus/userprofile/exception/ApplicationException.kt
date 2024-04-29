package ru.otus.userprofile.exception

import org.springframework.http.HttpStatus
import ru.otus.userprofile.dto.Error

class ApplicationException : RuntimeException {
    val error: Error

    constructor(_error: Error) {
        error = _error
    }

    constructor(code: HttpStatus, message: String) {
        error = Error(code.value(), message)
    }
}
