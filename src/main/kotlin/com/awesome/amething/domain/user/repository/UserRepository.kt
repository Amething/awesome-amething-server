package com.awesome.amething.domain.user.repository

import com.awesome.amething.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findAllByUsername(username: String): User
}
