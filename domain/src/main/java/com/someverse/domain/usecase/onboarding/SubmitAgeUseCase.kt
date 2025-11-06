package com.someverse.domain.usecase.onboarding

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
     * @param age User's age (must be 14 or older for service use)
     * @return Result<Unit> success or failure with validation error
     */
    suspend operator fun invoke(age: Int): Result<Unit> {
        // Business logic: Validate age
        if (age < 19) {
            return Result.failure(
                IllegalArgumentException("Must be at least 14 years old to use this service")
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