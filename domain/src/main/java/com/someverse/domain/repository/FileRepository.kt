package com.someverse.domain.repository

import com.someverse.domain.model.UploadedFile
import java.io.File

/**
 * File Repository Interface
 * - Handles file upload/download operations via AWS S3
 * - Uploads files directly to S3 and returns URLs
 * - URLs are then sent to backend API
 * - Generic repository for reusability
 * - Implementation will be in data layer
 *
 * Flow:
 * 1. Client (Android) -> S3 SDK -> Upload to S3
 * 2. S3 returns file URL
 * 3. Client -> Backend API with URL (via AuthRepository/PostRepository/etc.)
 */
interface FileRepository {

    /**
     * Upload single image file to S3
     *
     * @param file File to upload
     * @param category Category for organizing files (e.g., "profile", "post", "background")
     * @return Result<UploadedFile> containing S3 URL and metadata
     *
     * Implementation:
     * - Upload to S3 using AWS SDK
     * - Return S3 URL (e.g., https://bucket.s3.region.amazonaws.com/profile/image.jpg)
     */
    suspend fun uploadImage(
        file: File,
        category: String = "general"
    ): Result<UploadedFile>
}