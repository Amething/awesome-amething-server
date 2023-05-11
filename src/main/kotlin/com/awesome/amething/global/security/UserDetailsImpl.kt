package com.awesome.amething.global.security

import com.awesome.amething.domain.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl (val user : User) : UserDetails{
      override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
          var grantedAuthorityRole : MutableList<String> = user.roles.map { roles -> roles.name } as MutableList<String>
          return grantedAuthorityRole.map {roleName -> SimpleGrantedAuthority (roleName) } as MutableList<out GrantedAuthority>
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
