package com.awesome.amething.global.security.service

import com.awesome.amething.global.enums.AuthenticatorRole
import com.awesome.amething.global.exception.AmethingException
import com.awesome.amething.global.exception.ErrorCode
import com.awesome.amething.global.security.jwt.provider.JwtProvider
import com.awesome.amething.global.security.model.LoginDto
import com.awesome.amething.global.util.Loggable
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userDetailsService: UserDetailsService,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder,
) : Loggable {

    fun login(model: LoginDto.Input): LoginDto.Result {
        val userInfo = try {
            userDetailsService.loadUserByUsername(model.username)
        } catch (e: UsernameNotFoundException) {
            logger.debug("User not found with username: '{}' in login", model.username)
            throw AmethingException(ErrorCode.INVALID_LOGIN_INFO)
        }

        if (!checkPassword(model, userInfo.password)) {
            throw AmethingException(ErrorCode.INVALID_LOGIN_INFO)
        }

        val roles = userInfo.authorities.map {
            AuthenticatorRole.valueOf(it.authority)
        }
        return jwtProvider.createAccessAndRefreshToken(roles)
            .toModel()
    }

    private fun checkPassword(model: LoginDto.Input, password: String?) =
        passwordEncoder.matches(model.password, password)
}
