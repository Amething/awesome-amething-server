package com.awesome.amething.global.security.config

import com.awesome.amething.global.security.filter.JwtAuthorizationFilter
import com.awesome.amething.global.security.jwt.provider.JwtProvider
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class JwtFilterConfig(
    private val jwtProvider: JwtProvider,
    private val userDetails: UserDetailsService,
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(http: HttpSecurity) {
        http.addFilterBefore(JwtAuthorizationFilter(jwtProvider, userDetails), UsernamePasswordAuthenticationFilter::class.java)
    }
}
