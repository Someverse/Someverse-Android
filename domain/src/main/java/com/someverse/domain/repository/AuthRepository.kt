package com.someverse.domain.repository

import com.someverse.domain.model.AuthToken
import com.someverse.domain.model.Gender
import com.someverse.domain.model.SocialProvider

/**
 * Authentication Repository Interface
 * - Handles authentication related operations
 * - Implementation will be in data layer
 * - Use Result<T> for error handling
 */
interface AuthRepository {

    // ==================== Authentication ====================

    /**
     * 소셜 로그인 (현재는 Kakao만. 추후 추가 가능)
     * @param provider Social login provider type
     * @param authCode Authorization code from social provider
     */
    suspend fun socialLogin(
        provider: SocialProvider,
        authCode: String
    ): Result<AuthToken>

    /**
     * 로그아웃
     */
    suspend fun logout(): Result<Unit>

    /**
     * 리프레시 토큰
     */
    suspend fun refreshToken(refreshToken: String): Result<AuthToken>

    /**
     * 로그인 상태 체크
     */
    suspend fun checkAuthStatus(): Result<Boolean>

    // ==================== Onboarding Steps ====================

    /**
     * 닉네임 제출 (Step 1)
     */
    suspend fun submitNickname(nickname: String): Result<Unit>

    /**
     * 성별 제출 (Step 2)
     */
    suspend fun submitGender(gender: Gender): Result<Unit>

    /**
     * 나이 제출 (Step 3)
     */
    suspend fun submitAge(age: Int): Result<Unit>

    /**
     * 주소 제출 (Step 4)
     */
    suspend fun submitAddress(address: String): Result<Unit>

    /**
     * Submit profile image URL (Step 5)
     *
     * @param imageUrl S3 URL from FileRepository upload
     *
     * Flow:
     * 1. File uploaded to S3 via FileRepository
     * 2. S3 returns URL (e.g., https://bucket.s3.region.amazonaws.com/profile/user123.jpg)
     * 3. This method sends URL to backend API
     * 4. Backend stores URL in database
     */
    suspend fun submitProfileImage(imageUrl: String): Result<Unit>
}