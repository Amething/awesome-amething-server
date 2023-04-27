package com.awesome.amething.global.security.config

import com.awesome.amething.global.security.filter.ExceptionHandlerFilter
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.context.SecurityContextHolderFilter
import org.springframework.stereotype.Component

@Component
class SecurityExceptionHandlerFilterConfig(
    private val exceptionHandlerFilter: ExceptionHandlerFilter
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.addFilterBefore(exceptionHandlerFilter, SecurityContextHolderFilter::class.java)
    }
}
