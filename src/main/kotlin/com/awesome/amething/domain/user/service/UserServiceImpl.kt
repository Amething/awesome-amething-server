package com.awesome.amething.domain.user.service

import com.awesome.amething.domain.user.dto.UserDto
import com.awesome.amething.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@Transactional
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserService {
    override fun userRegistration(userDto: UserDto) {
        userDto.password = this.passwordEncoder.encode(userDto.password)
        userRepository.save(userDto.toEntity())
    }
}
