package com.someverse.data.impl

import com.someverse.data.source.FileDataSource
import com.someverse.domain.repository.FileRepository
import java.io.File
import javax.inject.Inject

/**
 * FileRepository Implementation
 * - Depends on FileDataSource interface (not concrete implementation)
 * - Can use FileRemoteDataSource
 * - Handles error transformation and business logic coordination
 * - Converts data layer exceptions to domain layer Results
 */
class FileRepositoryImpl @Inject constructor(
    private val dataSource: FileDataSource
) : FileRepository {

    override suspend fun uploadImage(file: File, folder: String): Result<String> {
        return try {
            val url = dataSource.uploadImage(file, folder)
            Result.success(url)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun uploadMultipleImages(
        files: List<File>,
        folder: String
    ): Result<List<String>> {
        return try {
            val urls = dataSource.uploadMultipleImages(files, folder)
            Result.success(urls)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteFile(fileUrl: String): Result<Unit> {
        return try {
            dataSource.deleteFile(fileUrl)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}