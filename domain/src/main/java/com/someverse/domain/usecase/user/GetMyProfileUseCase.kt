package com.someverse.domain.usecase.user

import com.someverse.domain.model.User
import com.someverse.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Get My Profile UseCase
 * GET /users/profile/me
 */
class GetMyProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<User> {
        return userRepository.getMyProfile()
    }
}
