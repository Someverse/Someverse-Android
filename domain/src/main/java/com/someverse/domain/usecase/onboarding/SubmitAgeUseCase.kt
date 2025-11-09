package com.someverse.domain.usecase.onboarding

import com.someverse.domain.model.User
import com.someverse.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Submit Age Use Case
 * - Single Responsibility: Handle age submission with validation
 * - Business logic: Validate age range
 * - Delegates to AuthRepository
 */
class SubmitAgeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Submit user age
     *
     * @param age User's age (must be 19 or older for service use)
     * @return Result<User> updated user with age or failure with validation error
     */
    suspend operator fun invoke(age: Int): Result<User> {
        // Business logic: Validate age
        if (age < 19) {
            return Result.failure(
                IllegalArgumentException("Must be at least 19 years old to use this service")
            )
        }

        if (age > 100) {
            return Result.failure(
                IllegalArgumentException("Invalid age value")
            )
        }

        // Delegate to repository
        return authRepository.submitAge(age)
    }
}