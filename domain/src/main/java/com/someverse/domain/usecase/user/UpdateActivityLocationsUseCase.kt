package com.someverse.domain.usecase.user

import com.someverse.domain.model.Location
import com.someverse.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Update Activity Locations UseCase
 * PUT /users/profile/me/locations
 */
class UpdateActivityLocationsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(locations: List<Location>): Result<Unit> {
        return userRepository.updateActivityLocations(locations)
    }
}
