package com.someverse.domain.usecase.auth

import com.someverse.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Logout Use Case
 * - Handles user logout
 * - Clears authentication state
 */
class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Logout current user
     *
     * @return Result<Unit> success or failure
     */
    suspend operator fun invoke(): Result<Unit> {
        // Business logic: Pre-logout actions
        // 예: 로컬 데이터 정리, 로그 기록 등

        return authRepository.logout()
    }
}