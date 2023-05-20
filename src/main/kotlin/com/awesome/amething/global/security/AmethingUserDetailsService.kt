package com.awesome.amething.global.security

import com.awesome.amething.domain.user.service.UserQueryService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AmethingUserDetailsService(
    private val userQueryService: UserQueryService
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userQueryService.getUserByUsername(username)
        return AmethingUserDetails(user)
    }
}
