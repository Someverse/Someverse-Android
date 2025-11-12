package com.someverse.domain.usecase.onboarding

import com.someverse.domain.model.Location
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
     * @param locations List of user's locations (max 2)
     * @return Result<User> updated user with addresses or failure with validation error
     */
    suspend operator fun invoke(locations: List<Location>): Result<User> {
        // Business logic: Validate locations
        if (locations.isEmpty()) {
            return Result.failure(
                IllegalArgumentException("At least one address must be provided")
            )
        }

        if (locations.size > MAX_ADDRESSES) {
            return Result.failure(
                IllegalArgumentException("Maximum number of addresses exceeded")
            )
        }


        // Delegate to repository
        // Convert Location objects to address strings for backward compatibility
        val addressStrings = locations.map { "${it.city} ${it.district}" }
        return authRepository.submitAddress(addressStrings)
    }

    companion object {
        private const val MAX_ADDRESSES = 2
    }
}