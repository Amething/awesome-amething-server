package com.awesome.amething.domain.user.service

import com.awesome.amething.domain.user.dto.UserDto

interface UserService {
    fun userRegistration(userDto: UserDto)
}