package com.awesome.amething.global.security

import com.awesome.amething.domain.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(
    private val user: User
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        val grantedAuthorityRole = user.roles.map {
            roles -> roles.name
        }
        return grantedAuthorityRole.map {
            roleName -> SimpleGrantedAuthority(roleName)
        }
    }

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}
