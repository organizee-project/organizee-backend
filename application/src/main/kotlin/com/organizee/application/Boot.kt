package com.organizee.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*

@SpringBootApplication
@EnableJpaRepositories("com.organizee.adapter.db.repositories")
@EntityScan("com.organizee.adapter.db.entities")
class Boot {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"))
            runApplication<Boot>(*args)
        }
    }
}