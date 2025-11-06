package com.someverse.domain.model

/**
 * Uploaded File Information
 * - Result of file upload operation
 * - Contains file URL and metadata
 */
data class UploadedFile(
    val url: String,
    val fileName: String,
    val fileSize: Long,
    val mimeType: String,
    val uploadedAt: Long = System.currentTimeMillis()
)