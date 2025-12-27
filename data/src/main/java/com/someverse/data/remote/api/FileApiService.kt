package com.someverse.data.remote.api

import com.someverse.data.model.ApiResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

/**
 * File API Service
 * - Retrofit interface for file upload/delete endpoints
 * - Handles multipart/form-data for file uploads
 */
interface FileApiService {
    /**
     * Upload single image file
     *
     * POST /files/upload?folder={folder}
     *
     * @param folder Query parameter for file organization
     * @param file Multipart file data
     * @return S3 URL string
     */
    @Multipart
    @POST("files/upload")
    suspend fun uploadImage(
        @Query("folder") folder: String,
        @Part file: MultipartBody.Part,
    ): Response<ApiResponse<String>>

    /**
     * Upload multiple image files
     *
     * POST /files/upload/multiple?folder={folder}
     *
     * @param folder Query parameter for file organization
     * @param files List of multipart file data (max 6)
     * @return List of S3 URLs
     */
    @Multipart
    @POST("files/upload/multiple")
    suspend fun uploadMultipleImages(
        @Query("folder") folder: String,
        @Part files: List<MultipartBody.Part>,
    ): Response<ApiResponse<List<String>>>

    /**
     * Delete file from S3
     *
     * DELETE /files/delete?fileUrl={fileUrl}
     *
     * @param fileUrl Full S3 URL of the file to delete
     * @return Unit (success message in ApiResponse)
     */
    @DELETE("files/delete")
    suspend fun deleteFile(
        @Query("fileUrl") fileUrl: String,
    ): Response<ApiResponse<Unit>>
}
