package com.awesome.amething.domain.user.dto

import com.awesome.amething.domain.user.User
import jakarta.validation.constraints.NotBlank

data class UserDto(
    @field:NotBlank
    val username: String,
    @field:NotBlank
    val password: String,
    @field:NotBlank
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
