package com.someverse.data.remote.api

import com.someverse.data.model.AuthTokenEntity
import retrofit2.Response
import retrofit2.http.*

/**
 * Auth API Service
 * - Retrofit interface for authentication endpoints
 * - Uses flexible parameter types (Map, individual fields)
 * - DataSource is responsible for creating request bodies
 * 스펙 확정되면 Map -> 개별 필드 혹은 DTO
 */
interface AuthApiService {

    // ==================== Authentication ====================

    /**
     * Social login
     * @param body Map containing "provider" and "authCode"
     */
    @POST("auth/social-login")
    suspend fun socialLogin(
        @Body body: Map<String, String>
    ): Response<AuthTokenEntity>

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>

    /**
     * Refresh token
     * @param body Map containing "refreshToken"
     */
    @POST("auth/refresh")
    suspend fun refreshToken(
        @Body body: Map<String, String>
    ): Response<AuthTokenEntity>

    /**
     * Check authentication status
     */
    @GET("auth/status")
    suspend fun checkAuthStatus(): Response<Map<String, Boolean>>

    // ==================== Onboarding Steps ====================

    /**
     * Submit nickname
     * @param body Map containing "nickname"
     */
    @PUT("onboarding/nickname")
    suspend fun submitNickname(
        @Body body: Map<String, String>
    ): Response<Unit>

    /**
     * Submit gender
     * @param body Map containing "gender"
     */
    @PUT("onboarding/gender")
    suspend fun submitGender(
        @Body body: Map<String, String>
    ): Response<Unit>

    /**
     * Submit age
     * @param body Map containing "age"
     */
    @PUT("onboarding/age")
    suspend fun submitAge(
        @Body body: Map<String, Int>
    ): Response<Unit>

    /**
     * Submit address
     * @param body Map containing "addresses" list
     */
    @PUT("onboarding/address")
    suspend fun submitAddress(
        @Body body: Map<String, List<String>>
    ): Response<Unit>

    /**
     * Submit profile image
     * @param body Map containing "imageUrl"
     */
    @PUT("onboarding/profile-image")
    suspend fun submitProfileImage(
        @Body body: Map<String, String>
    ): Response<Unit>
}