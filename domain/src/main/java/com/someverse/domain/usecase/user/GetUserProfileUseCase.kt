package com.someverse.domain.usecase.user

import com.someverse.domain.model.User
import com.someverse.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Get User Profile UseCase
 * GET /users/profile/{userId}
 */
class GetUserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userId: Long): Result<User> {
        return userRepository.getUserProfile(userId)
    }
}
