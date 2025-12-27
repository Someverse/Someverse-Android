package com.someverse.domain.repository

import com.someverse.domain.model.*

/**
 * User Repository Interface
 */
interface UserRepository {
    // Profile Management

    /**
     * Get my complete profile
     * GET /users/profile/me
     */
    suspend fun getMyProfile(): Result<User>

    /**
     * Get another user's profile
     * GET /users/profile/{userId}
     */
    suspend fun getUserProfile(userId: Long): Result<User>

    // Profile Image Management

    /**
     * Get profile image list
     * GET /users/profile/me/images
     */
    suspend fun getProfileImages(): Result<ProfileImages>

    /**
     * Add profile images
     * POST /users/profile/me/images
     */
    suspend fun addProfileImages(request: List<String>): Result<Unit>

    /**
     * Delete specific image by index
     * DELETE /users/profile/me/images/{index}
     */
    suspend fun deleteProfileImage(index: Int): Result<Unit>

    /**
     * Change primary image
     * PUT /users/profile/me/images/primary
     */
    suspend fun changePrimaryImage(request: Int): Result<Unit>

    /**
     * Delete multiple images
     * DELETE /users/profile/me/images/batch
     */
    suspend fun deleteMultipleImages(request: List<Int>): Result<Unit>

    /**
     * Update activity locations
     * PUT /users/profile/me/locations
     */
    suspend fun updateActivityLocations(request: List<Location>): Result<Unit>
}
