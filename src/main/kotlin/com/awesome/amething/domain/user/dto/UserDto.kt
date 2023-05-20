package com.awesome.amething.domain.user.dto

import com.awesome.amething.domain.user.User

data class UserDto(
    val username: String,
    val password: String,
    val nickName: String,
    val bio: String,
) {
    fun toEntity(): User {
        return User(
            username = username,
            password = password,
            nickName = nickName,
            bio = bio,
        )
    }
}
