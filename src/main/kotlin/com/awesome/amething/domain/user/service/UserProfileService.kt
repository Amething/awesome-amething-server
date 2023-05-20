package com.awesome.amething.domain.user.service

import com.awesome.amething.domain.user.model.UpdateUserProfileModel
import com.awesome.amething.domain.user.model.UserProfile
import com.awesome.amething.domain.user.repository.UserRepository
import com.awesome.amething.global.exception.AmethingException
import com.awesome.amething.global.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserProfileService(
    private val userRepository: UserRepository,
) {
    @Transactional(readOnly = true)
    fun findUserProfile(username: String): UserProfile {
        val user = (userRepository.findByUsername(username)
            ?: throw AmethingException(ErrorCode.USER_NOT_FOUND))
        return UserProfile(
            username = user.username,
            nickname = user.nickname,
            bio = user.bio,
            profilePicture = user.profilePicture
        )
    }
    fun updateUserProfile(username: String, model: UpdateUserProfileModel.Model) {
        val user = userRepository.findByUsername(username)
            ?: throw AmethingException(ErrorCode.USER_NOT_FOUND)
        user.updateProfile(
            nickname = model.nickname,
            bio = model.bio,
            profilePicture = model.profilePicture
        )
    }
}
