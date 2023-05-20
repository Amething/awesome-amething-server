package com.awesome.amething.domain.user.service

import com.awesome.amething.domain.user.User
import com.awesome.amething.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserQueryService(
    private val userRepository: UserRepository,
) {
    fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username)
    }
}
