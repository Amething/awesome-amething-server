package com.awesome.amething.domain.user.service

import com.awesome.amething.domain.user.dto.UserDto
import com.awesome.amething.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
){
    fun register(userDto: UserDto): Long{
        val encodedPassword = this.passwordEncoder.encode(userDto.password)
        val savedUser = userRepository.save(
            userDto.toEntity(encodedPassword)
        )
        return savedUser.id!!
    }
}
