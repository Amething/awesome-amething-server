package com.awesome.amething.domain.user.controller

import com.awesome.amething.domain.user.dto.UserDto
import com.awesome.amething.domain.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/user")
class UserController @Autowired constructor(
    private val userService: UserService,
) {
    @PostMapping("/registration")
    fun userRegistration(@RequestBody userDto: UserDto): ResponseEntity<HttpStatus> {
        userService.userRegistration(userDto)
        return ResponseEntity(HttpStatus.OK)
    }
}
