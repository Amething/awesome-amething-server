package com.awesome.amething.domain.user.dto

import org.jetbrains.annotations.NotNull

data class UserDto(

    @field:NotNull
    var username : String,
    @field:NotNull
    var password : String,

    var nickName : String,

    var bio : String
){
    fun toEntity() : com.awesome.amething.domain.user.User {
        return com.awesome.amething.domain.user.User(
            username = username,
            password = password,
            nickName = nickName,
            bio = bio
        )
    }
}
