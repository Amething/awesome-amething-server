package com.awesome.amething.domain.user.model

import jakarta.validation.constraints.NotBlank

class UpdateUserProfileModel {
    data class Model(
        @NotBlank
        val nickname: String,
        val bio: String,
        val profilePicture: String,
    )
}
