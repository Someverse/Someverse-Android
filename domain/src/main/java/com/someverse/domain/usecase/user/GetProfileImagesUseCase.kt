package com.someverse.domain.usecase.user

import com.someverse.domain.model.ProfileImages
import com.someverse.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Get Profile Images UseCase
 * GET /users/profile/me/images
 */
class GetProfileImagesUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<ProfileImages> {
        return userRepository.getProfileImages()
    }
}
