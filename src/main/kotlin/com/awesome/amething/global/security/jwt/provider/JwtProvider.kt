package com.awesome.amething.global.security.jwt.provider

import com.awesome.amething.global.enums.AuthenticatorRole
import com.awesome.amething.global.security.jwt.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.util.Base64
import java.util.Date

@Component
class JwtProvider(
    private val jwtProperties: JwtProperties,
) {

    private val secretKeyEncodingBase64: String =
        Base64.getEncoder().encodeToString(jwtProperties.secretKey.toByteArray())

    fun createAccessToken(roles: List<AuthenticatorRole>): String {
        val accessTokenExpireTime = Date.from(
            OffsetDateTime.now()
                .plus(jwtProperties.accessTokenExpireTime)
                .toInstant(),
        )

        val claims: Claims = Jwts.claims().apply {
            this["roles"] = roles
            this.expiration = accessTokenExpireTime
        }

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date.from(OffsetDateTime.now().toInstant()))
            .setExpiration(accessTokenExpireTime)
            .signWith(Keys.hmacShaKeyFor(secretKeyEncodingBase64.toByteArray()))
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

    fun getOauthId(token: String): String {
        return parseClaims(token).subject
    }
}
