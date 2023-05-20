package com.awesome.amething.domain.user.service

import com.awesome.amething.domain.user.dto.UserRegisterDto
import com.awesome.amething.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserRegistrationService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun register(userRegisterDto: UserRegisterDto): Long {
        val encodedPassword = this.passwordEncoder.encode(userRegisterDto.password)
        val savedUser = userRepository.save(
            userRegisterDto.toEntity(encodedPassword),
        )
        return savedUser.id
    }
}
