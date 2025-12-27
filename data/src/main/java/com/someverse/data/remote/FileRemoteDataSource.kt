package com.someverse.data.remote

import com.someverse.data.remote.api.FileApiService
import com.someverse.data.source.FileDataSource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

/**
 * File Remote DataSource (Real API Implementation)
 * - Implements FileDataSource interface
 * - Handles file upload/delete operations via backend API
 * - Converts File to MultipartBody.Part for Retrofit
 */
@Singleton
class FileRemoteDataSource
    @Inject
    constructor(
        private val fileApiService: FileApiService,
    ) : FileDataSource {
        override suspend fun uploadImage(
            file: File,
            folder: String,
        ): String {
            // Convert File to MultipartBody.Part
            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
            val multipartBody =
                MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    requestBody,
                )

            // Call API
            val response = fileApiService.uploadImage(folder, multipartBody)

            // Handle response
            if (response.isSuccessful && response.body()?.success == true) {
                return response.body()?.data
                    ?: throw Exception("Upload successful but no URL returned")
            } else {
                val errorMessage = response.body()?.message ?: "Upload failed"
                throw Exception(errorMessage)
            }
        }

        override suspend fun uploadMultipleImages(
            files: List<File>,
            folder: String,
        ): List<String> {
            // Convert List<File> to List<MultipartBody.Part>
            val multipartBodies =
                files.map { file ->
                    val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData(
                        "files", // Parameter name for multiple files
                        file.name,
                        requestBody,
                    )
                }

            // Call API
            val response = fileApiService.uploadMultipleImages(folder, multipartBodies)

            // Handle response
            if (response.isSuccessful && response.body()?.success == true) {
                return response.body()?.data
                    ?: throw Exception("Upload successful but no URLs returned")
            } else {
                val errorMessage = response.body()?.message ?: "Multiple upload failed"
                throw Exception(errorMessage)
            }
        }

        override suspend fun deleteFile(fileUrl: String) {
            // Call API
            val response = fileApiService.deleteFile(fileUrl)

            // Handle response
            if (response.isSuccessful && response.body()?.success == true) {
                // Success - do nothing
                return
            } else {
                val errorMessage = response.body()?.message ?: "Delete failed"
                throw Exception(errorMessage)
            }
        }
    }
