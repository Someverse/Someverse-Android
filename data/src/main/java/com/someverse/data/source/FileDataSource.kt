package com.someverse.data.source

import java.io.File

/**
 * File DataSource Interface
 * - Abstract data access operations for file management
 * - Can be implemented by Remote (API) or Local (mock)
 * - Repository depends on this interface, not concrete implementations
 */
interface FileDataSource {
    /**
     * Upload single image file
     *
     * @param file File to upload
     * @param folder Folder for organizing files
     * @return S3 URL string
     */
    suspend fun uploadImage(
        file: File,
        folder: String,
    ): String

    /**
     * Upload multiple image files
     *
     * @param files List of files to upload (max 6)
     * @param folder Folder for organizing files
     * @return List of S3 URLs
     */
    suspend fun uploadMultipleImages(
        files: List<File>,
        folder: String,
    ): List<String>

    /**
     * Delete file from S3
     *
     * @param fileUrl Full S3 URL of the file to delete
     */
    suspend fun deleteFile(fileUrl: String)
}
