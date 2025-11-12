package com.someverse.domain.usecase.onboarding

import com.someverse.domain.model.Location
import com.someverse.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Get Address List Use Case
 * - Retrieves a list of available locations for user selection
 * - Delegates to AuthRepository
 */
class GetAddressListUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    /**
     * Get list of available locations (city + district)
     *
     * @return Result<List<Location>> list of locations or failure
     *
     * Example response structure:
     * {
     *   "locations": [
     *     {
     *       "city": "서울특별시",
     *       "district": "강남구"
     *     },
     *     {
     *       "city": "경기도",
     *       "district": "광명시"
     *     }
     *   ]
     * }
     */
    suspend operator fun invoke(): Result<List<Location>> {
        return authRepository.getAddressList()
    }
}