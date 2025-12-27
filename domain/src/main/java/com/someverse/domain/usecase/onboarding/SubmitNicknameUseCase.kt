package com.someverse.domain.usecase.onboarding

import com.someverse.domain.model.User
import com.someverse.domain.repository.AuthRepository
import javax.inject.Inject

/**
 * Submit Nickname Use Case
 * - Single Responsibility: Handle nickname submission with validation
 * - Business logic: Validate nickname format and length
 * - Delegates to AuthRepository
 */
class SubmitNicknameUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        /**
         * Submit user nickname
         *
         * @param nickname User's chosen nickname, 2~8자
         * @return Result<User> updated user with nickname or failure with validation error
         */
        suspend operator fun invoke(nickname: String): Result<User> {
            // Business logic: Validate nickname
            if (nickname.isBlank()) {
                return Result.failure(
                    IllegalArgumentException("Nickname cannot be blank"),
                )
            }

            if (nickname.length < 2) {
                return Result.failure(
                    IllegalArgumentException("Nickname must be at least 2 characters"),
                )
            }

            if (nickname.length > 8) {
                return Result.failure(
                    IllegalArgumentException("Nickname must be less than 9 characters"),
                )
            }

            // Delegate to repository
            return authRepository.submitNickname(nickname)
        }
    }
