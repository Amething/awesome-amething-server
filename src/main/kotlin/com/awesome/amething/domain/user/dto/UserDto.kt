package com.awesome.amething.domain.user.dto

import com.awesome.amething.domain.user.User

data class UserDto(
    val username: String,
    val password: String,
    val nickname: String,
    val bio: String,
) {
    fun toEntity(password: String): User {
        return User(
            username = this.username,
            password = password,
            nickname = this.nickname,
            bio = this.bio,
        )
    }
}
