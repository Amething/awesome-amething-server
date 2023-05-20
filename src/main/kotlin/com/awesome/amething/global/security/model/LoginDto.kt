package com.awesome.amething.global.security.model

import jakarta.validation.constraints.NotBlank
import java.time.OffsetDateTime

class LoginDto {
    data class Input(
        @field:NotBlank
        val username: String,
        @field:NotBlank
        val password: String,
    )

    data class Result(
        val accessToken: String,
        val accessTokenExpire: OffsetDateTime,
        val refreshToken: String,
        val refreshTokenExpire: OffsetDateTime,
    )
}
