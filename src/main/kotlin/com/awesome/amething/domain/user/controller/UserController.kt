package com.awesome.amething.domain.user.controller

import com.awesome.amething.domain.user.dto.UserRegisterDto
import com.awesome.amething.domain.user.model.UserProfile
import com.awesome.amething.domain.user.service.UserProfileService
import com.awesome.amething.domain.user.service.UserRegistrationService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController @Autowired constructor(
    private val userRegistrationService: UserRegistrationService,
    private val userProfileService: UserProfileService,
) {
    @PostMapping("/register")
    fun register(
        @RequestBody
        @Valid
        userRegisterDto: UserRegisterDto,
    ): ResponseEntity<Unit> {
        userRegistrationService.register(userRegisterDto)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{username}")
    fun findProfile(
        @PathVariable
        username: String,
    ): ResponseEntity<UserProfile> =
        ResponseEntity.ok(userProfileService.findUserProfile(username))

}
