package com.awesome.amething.global.security.config

import com.awesome.amething.global.security.handler.SecurityAccessDeniedHandler
import com.awesome.amething.global.security.handler.SecurityAuthenticationEntryPointHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val jwtFilterConfig: JwtFilterConfig,

    private val securityExceptionHandlerFilterConfig: SecurityExceptionHandlerFilterConfig,

    private val securityAccessDeniedHandler: SecurityAccessDeniedHandler,
    private val securityAuthenticationEntryPointHandler: SecurityAuthenticationEntryPointHandler,
) {

    @Bean
    protected fun configure(http: HttpSecurity): SecurityFilterChain {
        http.cors().and()
            .csrf().disable()
            .httpBasic().disable()

        http.headers()
            .frameOptions()
            .sameOrigin().and();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeHttpRequests()
            .anyRequest().permitAll()
        http.exceptionHandling()
            .accessDeniedHandler(securityAccessDeniedHandler)
            .authenticationEntryPoint(securityAuthenticationEntryPointHandler)
        http.apply(jwtFilterConfig)
        http.apply(securityExceptionHandlerFilterConfig)
        return http.build()
    }
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
