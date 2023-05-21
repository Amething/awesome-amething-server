package com.awesome.amething.global.security.filter

import com.awesome.amething.global.security.jwt.provider.JwtProvider
import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthorizationFilter(
    private val jwtProvider: JwtProvider,
    private val userDetails: UserDetailsService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authorizationHeaderValue = request.getHeader(AUTHORIZATION_HEADER)
        if (!authorizationHeaderValue.isNullOrEmpty()) {
            val jwt = resolveToken(authorizationHeaderValue)
            if(!jwtProvider.validateToken(jwt))
                throw JwtException("Invalid Token")
            SecurityContextHolder.getContext().authentication = getAuthentication(jwt)
        }
        filterChain.doFilter(request, response)
    }

    private fun resolveToken(headerValue: String): String {
        return if (headerValue.isNotEmpty() && headerValue.startsWith(PREFIX)) {
            headerValue.removePrefix(PREFIX)
        } else {
            throw JwtException("Invalid Token")
        }
    }

    private fun getAuthentication(accessToken: String): Authentication {
        val userDetails: UserDetails = userDetails.loadUserByUsername(jwtProvider.getUsername(accessToken))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
        const val PREFIX = "Bearer "
    }
}
