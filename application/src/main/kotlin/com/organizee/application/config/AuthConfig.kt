package com.organizee.application.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


class AuthConfig {
}

@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v1/users/**").permitAll()
            .antMatchers("/v1/search/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/guides/**").permitAll()
            .anyRequest().authenticated().and().csrf().disable()

        http.oauth2ResourceServer()
            .jwt()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/swagger-ui/**",
        )
    }
}