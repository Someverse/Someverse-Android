package com.someverse.data.remote

import com.someverse.data.model.AuthTokenEntity
import com.someverse.data.model.UserEntity
import com.someverse.data.remote.api.AuthApiService
import com.someverse.data.source.AuthDataSource
import com.someverse.domain.model.Gender
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.NotImplementedError

/**
 * Auth Remote DataSource (Real API Implementation)
 * - Implements AuthDataSource interface
 * - TODO: Implement when backend API is ready
 * - Currently throws NotImplementedError
 * - Use AuthLocalDataSource for local development
 */
@Singleton
class AuthRemoteDataSource @Inject constructor(
    private val authApiService: AuthApiService
) : AuthDataSource {

    // For caching
    private var cachedToken: AuthTokenEntity? = null
    private var cachedUser: UserEntity? = null

    // ==================== Authentication ====================

    override suspend fun getAuthStatus(): Pair<UserEntity, Boolean> {
        throw NotImplementedError("Remote API not implemented yet. Use AuthLocalDataSource.")
    }

    override suspend fun logout() {
        throw NotImplementedError("Remote API not implemented yet. Use AuthLocalDataSource.")
    }

    override suspend fun refreshToken(refreshToken: String): AuthTokenEntity {
        throw NotImplementedError("Remote API not implemented yet. Use AuthLocalDataSource.")
    }

    override suspend fun isAuthenticated(): Boolean {
        return cachedToken != null && cachedUser != null
    }

    override suspend fun clearAuth() {
        cachedToken = null
        cachedUser = null
    }

    override suspend fun getToken(): AuthTokenEntity? {
        return cachedToken
    }

    override suspend fun saveToken(token: AuthTokenEntity) {
        cachedToken = token
    }

    // ==================== Onboarding Data ====================

    override suspend fun submitNickname(nickname: String): UserEntity {
        throw NotImplementedError("Remote API not implemented yet. Use AuthLocalDataSource.")
    }

    override suspend fun submitGender(gender: Gender): UserEntity {
        throw NotImplementedError("Remote API not implemented yet. Use AuthLocalDataSource.")
    }

    override suspend fun submitAge(age: Int): UserEntity {
        throw NotImplementedError("Remote API not implemented yet. Use AuthLocalDataSource.")
    }

    override suspend fun submitAddress(address: List<String>): UserEntity {
        throw NotImplementedError("Remote API not implemented yet. Use AuthLocalDataSource.")
    }

    override suspend fun submitProfileImage(imageUrl: String): UserEntity {
        throw NotImplementedError("Remote API not implemented yet. Use AuthLocalDataSource.")
    }

    // ==================== User Management ====================

    override suspend fun getCurrentUser(): UserEntity? {
        return cachedUser
    }

    override suspend fun saveUser(user: UserEntity) {
        cachedUser = user
    }

    override suspend fun getAddressList(): List<com.someverse.domain.model.Location> {
        // TODO: Implement real API call when backend is ready
        throw NotImplementedError("Remote API not implemented yet. Use AuthLocalDataSource.")
    }
}