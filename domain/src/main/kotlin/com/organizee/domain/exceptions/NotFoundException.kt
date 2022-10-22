package com.organizee.domain.exceptions

class NotFoundException(override val message: String) :
    GenericException(message) {
}