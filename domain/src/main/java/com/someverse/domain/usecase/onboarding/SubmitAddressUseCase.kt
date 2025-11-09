package com.someverse.domain.usecase.onboarding

import com.someverse.domain.model.User
import com.someverse.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Submit Address Use Case
 * - Single Responsibility: Handle address submission with validation
 * - Business logic: Validate multiple addresses format
 * - Delegates to AuthRepository
 */
class SubmitAddressUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Submit user addresses
     *
     * @param addresses List of user's addresses (max 2)
     * @return Result<User> updated user with addresses or failure with validation error
     */
    suspend operator fun invoke(addresses: List<String>): Result<User> {
        // Business logic: Validate addresses
        if (addresses.isEmpty()) {
            return Result.failure(
                IllegalArgumentException("At least one address must be provided")
            )
        }

        if (addresses.size > MAX_ADDRESSES) {
            return Result.failure(
                IllegalArgumentException("Maximum number of addresses exceeded")
            )
        }


        // Delegate to repository
        return authRepository.submitAddress(addresses.first())
    }

    companion object {
        private const val MAX_ADDRESSES = 2
    }
}