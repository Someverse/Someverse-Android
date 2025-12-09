package com.someverse.data.local

import com.someverse.data.source.FileDataSource
import java.io.File
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * File Local DataSource (Mock Implementation)
 * - For local development and testing
 * - Returns fake S3 URLs
 * - Does not actually upload files
 * - TODO: Replace with FileRemoteDataSource when backend is ready
 */
@Singleton
class FileLocalDataSource @Inject constructor() : FileDataSource {

    private val mockS3BaseUrl = "https://someverse-s3-test.s3.ap-northeast-2.amazonaws.com"
    private val uploadedFiles = mutableListOf<String>() // Track uploaded files

    override suspend fun uploadImage(file: File, folder: String): String {
        // Simulate upload delay
        kotlinx.coroutines.delay(500)

        // Generate fake S3 URL
        val dateFolder = java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.getDefault())
            .format(java.util.Date())
        val uuid = UUID.randomUUID().toString()
        val extension = file.extension
        val mockUrl = "$mockS3BaseUrl/$folder/$dateFolder/${uuid}_${file.name}"

        uploadedFiles.add(mockUrl)
        return mockUrl
    }

    override suspend fun uploadMultipleImages(files: List<File>, folder: String): List<String> {
        // Validate file count
        if (files.size > 6) {
            throw IllegalArgumentException("파일은 최대 6개까지 업로드 가능합니다.")
        }

        // Simulate upload delay
        kotlinx.coroutines.delay(1000)

        // Generate fake S3 URLs for each file
        val urls = files.map { file ->
            val dateFolder = java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.getDefault())
                .format(java.util.Date())
            val uuid = UUID.randomUUID().toString()
            val mockUrl = "$mockS3BaseUrl/$folder/$dateFolder/${uuid}_${file.name}"
            uploadedFiles.add(mockUrl)
            mockUrl
        }

        return urls
    }

    override suspend fun deleteFile(fileUrl: String) {
        // Simulate delete delay
        kotlinx.coroutines.delay(300)

        // Check if file exists in uploaded list
        if (!uploadedFiles.contains(fileUrl)) {
            // In mock, we'll just log it but not throw error
            println("Warning: File not found in uploaded list: $fileUrl")
        } else {
            uploadedFiles.remove(fileUrl)
        }
    }
}