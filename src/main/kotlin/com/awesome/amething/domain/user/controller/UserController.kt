package com.awesome.amething.domain.user.controller

import com.awesome.amething.domain.user.dto.UserDto
import com.awesome.amething.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/user")
class UserController @Autowired constructor(
    private val userService: UserService,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody
        @Valid
        userDto: UserDto
    ): ResponseEntity<Unit> {
        userService.register(userDto)
        return ResponseEntity.ok().build()
    }
}
