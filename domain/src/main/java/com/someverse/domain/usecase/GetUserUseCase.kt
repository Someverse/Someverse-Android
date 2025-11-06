package com.someverse.domain.usecase

import com.someverse.domain.model.User
import com.someverse.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Use Case - Single Responsibility
 * - Contains business logic
 * - Uses Repository interface
 * - operator fun invoke for clean syntax
 */
class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    /**
     * Get user by ID with validation
     *
     * @param userId User ID to fetch
     * @return Result<User> containing user or error
     */
    suspend operator fun invoke(userId: String): Result<User> {
        // Business logic: Validate input
        if (userId.isBlank()) {
            return Result.failure(
                IllegalArgumentException("User ID cannot be blank")
            )
        }

        // Delegate to repository
        return userRepository.getUser(userId)
    }
}
