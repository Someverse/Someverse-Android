package com.someverse.domain.usecase.user

import com.someverse.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Delete Multiple Images UseCase
 * DELETE /users/profile/me/images/batch
 */
class DeleteMultipleImagesUseCase
    @Inject
    constructor(
        private val userRepository: UserRepository,
    ) {
        suspend operator fun invoke(imageIndices: List<Int>): Result<Unit> = userRepository.deleteMultipleImages(imageIndices)
    }
