package com.someverse.domain.usecase.auth

import com.someverse.domain.model.AuthToken
import com.someverse.domain.model.SocialProvider
import com.someverse.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Social Login Use Case
 * - Single Responsibility: Handle social login for any provider
 * - Validates auth code
 * - Delegates to AuthRepository
 *
 * Usage:
 * ```
 * // Kakao
 * socialLoginUseCase(SocialProvider.KAKAO, authCode)
 */
class SocialLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Login with social provider
     *
     * @param provider Social login provider (KAKAO.. add more as needed)
     * @param authCode Authorization code from social provider SDK
     * @return Result<AuthToken> containing auth token or error
     */
    suspend operator fun invoke(
        provider: SocialProvider,
        authCode: String
    ): Result<AuthToken> {
        // Business logic: Validate input
        if (authCode.isBlank()) {
            return Result.failure(
                IllegalArgumentException("Auth code cannot be blank")
            )
        }

        // Delegate to repository
        return authRepository.socialLogin(
            provider = provider,
            authCode = authCode
        )
    }
}