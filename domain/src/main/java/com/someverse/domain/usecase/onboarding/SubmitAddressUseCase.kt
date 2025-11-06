package com.someverse.domain.usecase.onboarding

import com.someverse.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Submit Address Use Case
 * - Single Responsibility: Handle address submission with validation
 * - Business logic: Validate address format
 * - Delegates to AuthRepository
 */
class SubmitAddressUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Submit user address
     *
     * @param address User's address
     * @return Result<Unit> success or failure with validation error
     */
    suspend operator fun invoke(address: String): Result<Unit> {
        // Business logic: Validate address
        if (address.isBlank()) {
            return Result.failure(
                IllegalArgumentException("Address cannot be blank")
            )
        }

        if (address.length < 5) {
            return Result.failure(
                IllegalArgumentException("Address is too short")
            )
        }

        if (address.length > 200) {
            return Result.failure(
                IllegalArgumentException("Address is too long")
            )
        }

        // Delegate to repository
        return authRepository.submitAddress(address)
    }
}