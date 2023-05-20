package com.awesome.amething.global.security.controller

import com.awesome.amething.global.security.model.LoginDto
import com.awesome.amething.global.security.service.AuthService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val authService: AuthService,
) {

    @PostMapping("/login")
    fun login(
        @RequestBody
        @Valid
        request: LoginDto.Input,
    ): ResponseEntity<LoginDto.Result> {
        return ResponseEntity.ok(authService.login(request))
    }
}
