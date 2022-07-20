package com.organizee

interface Usecase<IN, OUT> {
    fun execute(input: IN): OUT
}