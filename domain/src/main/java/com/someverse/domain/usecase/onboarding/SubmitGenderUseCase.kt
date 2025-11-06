package com.someverse.domain.usecase.onboarding

import com.someverse.domain.model.Gender
import com.someverse.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Submit Gender Use Case
 * - Single Responsibility: Handle gender submission with validation
 * - Business logic: No validation needed - enum guarantees valid value
 * - Delegates to AuthRepository
 */
class SubmitGenderUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Submit user gender
     *
     * @param gender User's gender (MALE, FEMALE)
     * @return Result<Unit> success or failure with validation error
     */
    suspend operator fun invoke(gender: Gender): Result<Unit> {
        // No validation needed - enum guarantees valid value!
        // Business logic can be added here if needed
        // e.g., logging, analytics, etc.

        return authRepository.submitGender(gender)
    }
}