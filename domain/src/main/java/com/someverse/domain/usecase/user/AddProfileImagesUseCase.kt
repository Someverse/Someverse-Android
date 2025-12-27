package com.someverse.domain.usecase.user

import com.someverse.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Add Profile Images UseCase
 * POST /users/profile/me/images
 */
class AddProfileImagesUseCase
    @Inject
    constructor(
        private val userRepository: UserRepository,
    ) {
        suspend operator fun invoke(imageUrls: List<String>): Result<Unit> = userRepository.addProfileImages(imageUrls)
    }
