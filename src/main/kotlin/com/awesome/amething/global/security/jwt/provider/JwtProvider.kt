package com.awesome.amething.global.security.jwt.provider

import com.awesome.amething.global.enums.AuthenticatorRole
import com.awesome.amething.global.security.jwt.config.JwtProperties
import com.awesome.amething.global.security.jwt.model.TokenGenerateResult
import com.awesome.amething.global.util.Loggable
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.time.OffsetDateTime
import java.util.Base64
import java.util.Date

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
) {

    private val secretKeyEncodingBase64: String =
        Base64.getUrlEncoder().encodeToString(jwtProperties.secretKey.toByteArray())

    fun createAccessAndRefreshToken(roles: List<AuthenticatorRole>, username: String): TokenGenerateResult {
        val accessToken = createAccessToken(roles, username)
        val refreshToken = createRefreshToken()
        return TokenGenerateResult(
            accessToken = accessToken,
            accessTokenExpire = OffsetDateTime.now().plus(jwtProperties.accessTokenExpireTime),
            refreshToken = refreshToken,
            refreshTokenExpire = OffsetDateTime.now().plus(jwtProperties.refreshTokenExpireTime),
        )
    }

    fun createAccessToken(roles: List<AuthenticatorRole>, username: String): String {
        val accessTokenExpireTime = Date.from(
            OffsetDateTime.now()
                .plus(jwtProperties.accessTokenExpireTime)
                .toInstant(),
        )

        val claims: Claims = Jwts.claims().apply {
            this["roles"] = roles
            this["username"] = username
            this.expiration = accessTokenExpireTime
        }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(OffsetDateTime.now().toInstant()))
            .setExpiration(accessTokenExpireTime)
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun createRefreshToken(): String {
        val refreshTokenExpireTime = Date.from(
            OffsetDateTime.now()
                .plus(jwtProperties.refreshTokenExpireTime)
                .toInstant(),
        )

        val claims: Claims = Jwts.claims().apply {
            this.expiration = refreshTokenExpireTime
        }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(OffsetDateTime.now().toInstant()))
            .setExpiration(refreshTokenExpireTime)
            .signWith(Keys.hmacShaKeyFor(secretKeyEncodingBase64.toByteArray()))
            .compact()
    }

    fun validateToken(token: String): Boolean {
        val expirationTime: Date = parseClaims(token).expiration
        return expirationTime.after(Date())
    }

    private fun parseClaims(token: String): Claims {
        logger.debug("JWT='{}'", token)
        return try {
            Jwts.parserBuilder()
                .setSigningKey(
                    Keys.hmacShaKeyFor(secretKeyEncodingBase64.toByteArray()),
                ).build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            e.claims
        }
    }

    fun getUsername(token: String): String {
        return parseClaims(token)["username"] as String
    }

    private fun getSignInKey(): Key {
        val keyBytes = Encoders.BASE64.encode(jwtProperties.secretKey.toByteArray())
        return Keys.hmacShaKeyFor(keyBytes.toByteArray())
    }
    companion object : Loggable
}
