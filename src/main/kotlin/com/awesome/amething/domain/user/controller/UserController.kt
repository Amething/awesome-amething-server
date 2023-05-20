package com.awesome.amething.domain.user.controller

import com.awesome.amething.domain.user.dto.UserRegisterDto
import com.awesome.amething.domain.user.service.UserRegistrationService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/user")
class UserController @Autowired constructor(
    private val userRegistrationService: UserRegistrationService,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody
        @Valid
        userRegisterDto: UserRegisterDto
    ): ResponseEntity<Unit> {
        userRegistrationService.register(userRegisterDto)
        return ResponseEntity.ok().build()
    }
}
