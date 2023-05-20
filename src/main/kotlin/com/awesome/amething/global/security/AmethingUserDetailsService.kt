package com.awesome.amething.global.security

import com.awesome.amething.domain.user.service.UserQueryService
import com.awesome.amething.global.exception.AmethingException
import com.awesome.amething.global.exception.ErrorCode
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AmethingUserDetailsService(
    private val userQueryService: UserQueryService,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userQueryService.getUserByUsername(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")
        return AmethingUserDetails(user)
    }
}
