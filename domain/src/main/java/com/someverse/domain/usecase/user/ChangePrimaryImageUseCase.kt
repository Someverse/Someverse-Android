package com.someverse.domain.usecase.user

import com.someverse.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Change Primary Image UseCase
 * PUT /users/profile/me/images/primary
 */
class ChangePrimaryImageUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(primaryIndex: Int): Result<Unit> {
        return userRepository.changePrimaryImage(primaryIndex)
    }
}
