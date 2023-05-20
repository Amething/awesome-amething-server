package com.awesome.amething.global.security.jwt.model

import com.awesome.amething.global.security.model.LoginDto
import java.time.OffsetDateTime

class TokenGenerateResult(
    val accessToken: String,
    val accessTokenExpire: OffsetDateTime,
    val refreshToken: String,
    val refreshTokenExpire: OffsetDateTime,
) {
    fun toModel(): LoginDto.Result {
        return LoginDto.Result(
            accessToken = accessToken,
            accessTokenExpire = accessTokenExpire,
            refreshToken = refreshToken,
            refreshTokenExpire = refreshTokenExpire,
        )
    }
}
