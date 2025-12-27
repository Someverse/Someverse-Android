package com.someverse.data.model

import com.google.gson.annotations.SerializedName

/**
 * Generic API Response wrapper
 * - Standard response format from backend
 * - Contains success status, code, message, and data
 */
data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T?,
)
