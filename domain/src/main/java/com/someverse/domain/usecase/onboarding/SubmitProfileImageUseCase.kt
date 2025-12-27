package com.someverse.domain.usecase.onboarding

import com.someverse.domain.repository.AuthRepository
import com.someverse.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

/**
 * Submit Profile Image Use Case
 * - Single Responsibility: Handle profile image upload and submission
 * - Orchestrates FileRepository (S3 upload) and AuthRepository (backend API)
 * - Business logic: Validate image file and size
 *
 * Complete Flow:
 * ```
 * 1. User selects image file
 *    ↓
 * 2. UseCase validates file (size, format, exists)
 *    ↓
 * 3. FileRepository → AWS S3 SDK → Upload to S3
 *    ↓
 * 4. S3 returns URL (https://bucket.s3.amazonaws.com/profile/image.jpg)
 *    ↓
 * 5. AuthRepository → Backend API → Store URL in database
 *    ↓
 * 6. Backend returns success
 *    ↓
 * 7. UseCase returns URL to ViewModel
 * ```
 *
 * NOTE: THIS USECASE IS CURRENTLY DISABLED AND WILL BE IMPLEMENTED LATER
 * The FileRepository implementation is not yet available.
 */
// TODO: Implement FileRepository and uncomment this UseCase
/*
class SubmitProfileImageUseCase @Inject constructor(
    private val fileRepository: FileRepository,     // S3 upload
    private val authRepository: AuthRepository      // Backend API (URL only)
) {
    companion object {
        private const val MAX_FILE_SIZE = 10 * 1024 * 1024 // 10MB
        private val ALLOWED_EXTENSIONS = setOf("jpg", "png", "heic")
    }

    /**
 * Upload profile image to S3 and submit URL to backend
 *
 * @param imageFile Image file to upload
 * @return Result<String> containing S3 URL or error
 */
    suspend operator fun invoke(imageFile: File): Result<String> {
        // Business logic: Validate file exists
        if (!imageFile.exists()) {
            return Result.failure(
                IllegalArgumentException("Image file does not exist")
            )
        }

        // Business logic: Validate file size (max 10MB)
        if (imageFile.length() > MAX_FILE_SIZE) {
            return Result.failure(
                IllegalArgumentException("Image file is too large (max 10MB)")
            )
        }

        // Business logic: Validate file extension
        val extension = imageFile.extension.lowercase()
        if (extension !in ALLOWED_EXTENSIONS) {
            return Result.failure(
                IllegalArgumentException("Invalid image format. Allowed: ${ALLOWED_EXTENSIONS.joinToString()}")
            )
        }

        // Step 1: Upload image to S3 (FileRepository)
        // AWS SDK handles the actual upload
        val uploadResult = fileRepository.uploadImage(
            file = imageFile,
            category = "profile"  // S3 folder: s3://bucket/profile/
        )

        if (uploadResult.isFailure) {
            return Result.failure(
                uploadResult.exceptionOrNull() ?: Exception("Failed to upload image to S3")
            )
        }

        val uploadedFile = uploadResult.getOrNull()
            ?: return Result.failure(Exception("Upload result is null"))

        // uploadedFile.url = "https://someverse-bucket.s3.ap-northeast-2.amazonaws.com/profile/user123_1234567890.jpg"

        // Step 2: Submit S3 URL to backend API (AuthRepository)
        // Backend will store this URL in database
        val submitResult = authRepository.submitProfileImage(uploadedFile.url)

        return if (submitResult.isSuccess) {
            // Return S3 URL for UI to display
            Result.success(uploadedFile.url)
        } else {
            // Upload to S3 succeeded but backend API failed
            // Consider deleting from S3 (cleanup) - optional
            Result.failure(
                submitResult.exceptionOrNull()
                    ?: Exception("Failed to submit profile image to backend")
            )
        }
    }
}
*/

// Temporary implementation that always returns a mock URL
// This allows the app to compile and run without the actual file upload functionality
class SubmitProfileImageUseCase
    @Inject
    constructor(
        private val authRepository: AuthRepository,
    ) {
        suspend operator fun invoke(imageFile: File): Result<String> {
            // Mock implementation - always returns a successful result with a fake URL
            val mockUrl =
                "https://someverse-bucket.s3.amazonaws.com/profile/mock_image_${System.currentTimeMillis()}.jpg"

            // Submit mock URL to backend
            val submitResult = authRepository.submitProfileImage(mockUrl)

            return if (submitResult.isSuccess) {
                Result.success(mockUrl)
            } else {
                Result.failure(
                    submitResult.exceptionOrNull() ?: Exception("Failed to submit profile image"),
                )
            }
        }
    }
