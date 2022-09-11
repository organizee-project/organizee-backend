package com.organizee.shared.extensions

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule

class JacksonSerializer {
    companion object {
        private val mapper = ObjectMapper()
            .registerModule(ParameterNamesModule())
            .registerModule(Jdk8Module())
            .registerModule(JavaTimeModule())

        fun serialize(`object`: Any): String? {
            try {
                return mapper.writeValueAsString(`object`)
            } catch (e: JsonProcessingException) {
                e.printStackTrace()
            }
            return null
        }
    }


}