package com.organizee.domain.exceptions

class BussinessException(override val message: String) :
    GenericException(message) {
}