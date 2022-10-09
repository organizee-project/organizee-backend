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
            .antMatchers(HttpMethod.POST, "/v1/users").permitAll()
            .antMatchers("/v1/users/{username}/perfil").permitAll()
            .antMatchers("/v1/search/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/comments/guide/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/likes/guide/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/guides/**").permitAll()
            .anyRequest().authenticated().and().csrf().disable()

        http.oauth2ResourceServer()
            .jwt()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**"
        )
    }
}