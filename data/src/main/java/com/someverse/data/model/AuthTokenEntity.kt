package com.someverse.data.model

import com.google.gson.annotations.SerializedName
import com.someverse.domain.model.AuthToken

/**
 * AuthToken Entity (Data Layer)
 * - API response model for authentication
 * - Maps to domain AuthToken model
 * - Uses @SerializedName for snake_case API fields
 */
data class AuthTokenEntity(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("token_type")
    val tokenType: String = "Bearer",
) {
    /**
     * Convert AuthTokenEntity (data) to AuthToken (domain)
     */
    fun toDomain(): AuthToken =
        AuthToken(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expiresIn = expiresIn,
            tokenType = tokenType,
        )
}

/**
 * Extension function to convert domain AuthToken to AuthTokenEntity
 */
fun AuthToken.toEntity(): AuthTokenEntity =
    AuthTokenEntity(
        accessToken = accessToken,
        refreshToken = refreshToken,
        expiresIn = expiresIn,
        tokenType = tokenType,
    )
