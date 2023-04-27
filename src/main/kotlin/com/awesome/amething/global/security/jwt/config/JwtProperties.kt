package com.awesome.amething.global.security.jwt.config

import java.time.Duration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties @ConstructorBinding constructor(
    val accessTokenExpireTime: Duration,
    val refreshTokenExpireTime: Duration,
    val type: String,
    val secretKey: String
)
