package com.organizee.application.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

class AuthConfig {
}

@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v1/users/**").permitAll()
            .anyRequest().authenticated().and().csrf().disable()

        http.oauth2ResourceServer()
            .jwt()
    }
}