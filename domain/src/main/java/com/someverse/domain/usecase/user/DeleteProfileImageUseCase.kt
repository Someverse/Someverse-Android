package com.someverse.domain.usecase.user

import com.someverse.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Delete Profile Image UseCase
 * DELETE /users/profile/me/images/{index}
 */
class DeleteProfileImageUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(index: Int): Result<Unit> {
        return userRepository.deleteProfileImage(index)
    }
}
