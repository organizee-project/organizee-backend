package com.organizee.application.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration

@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v1/files").permitAll()
            .antMatchers("/v1/categories/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/users/logged").authenticated()
            .antMatchers(HttpMethod.GET, "/v1/users/{username}/**").permitAll()
            .antMatchers("/v1/search/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/comments/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/likes/guide/**").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/guides").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/guides/{slug}").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/guides/user/{username}/likes").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/saved/guides/{username}").permitAll()
            .antMatchers(HttpMethod.GET, "/v1/guides/feed/following").authenticated()
            .anyRequest().authenticated().and().csrf().disable()

        http.cors()
            .configurationSource {
                val corsConfiguration = CorsConfiguration().applyPermitDefaultValues()
                corsConfiguration.addAllowedMethod(HttpMethod.DELETE)
                corsConfiguration.addAllowedMethod(HttpMethod.PUT)
                corsConfiguration.addAllowedMethod(HttpMethod.PATCH)

                corsConfiguration
            }

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
