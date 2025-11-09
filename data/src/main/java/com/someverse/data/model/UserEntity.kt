package com.someverse.data.model

import com.google.gson.annotations.SerializedName
import com.someverse.domain.model.*

/**
 * User Entity (Data Layer)
 * - API response model from GET /users/profile/me
 * - Maps to domain User model
 * - Uses @SerializedName for API field mapping
 */
data class UserEntity(
    @SerializedName("userId")
    val id: String,

    @SerializedName("nickname")
    val nickname: String?,

    @SerializedName("age")
    val age: Int?,

    @SerializedName("gender")
    val gender: String?, // WOMAN, MAN, etc.

    @SerializedName("activityLocations")
    val activityLocations: List<LocationEntity>?,

    @SerializedName("profileImages")
    val profileImages: List<String>?,

    @SerializedName("primaryImageUrl")
    val primaryImageUrl: String?,

    @SerializedName("bio")
    val bio: String?,

    @SerializedName("job")
    val job: String?,

    @SerializedName("favoriteMovies")
    val favoriteMovies: List<MovieEntity>?,

    @SerializedName("preferredGenres")
    val preferredGenres: List<GenreEntity>?,

    // For OAuth response only (GET /users/me)
    @SerializedName("email")
    val email: String? = null,

    @SerializedName("realName")
    val realName: String? = null,

    @SerializedName("provider")
    val provider: String? = null, // KAKAO, GOOGLE, etc.

    @SerializedName("birthDate")
    val birthDate: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("onboardingCompleted")
    val onboardingCompleted: Boolean = false,

    @SerializedName("onboardingStep")
    val onboardingStep: Int? = null,

    @SerializedName("created_at")
    val createdAt: Long = 0,

    @SerializedName("updated_at")
    val updatedAt: Long = 0
) {
    /**
     * Convert UserEntity (data) to User (domain)
     * For full profile response
     */
    fun toDomain(): User {
        return User(
            id = id,
            provider = SocialProvider.fromString(provider),
            nickname = nickname,
            birthDate = birthDate,
            gender = gender?.let { Gender.valueOf(it) },
            activityLocations = activityLocations?.map { it.toDomain() },
            profileImages = profileImages,
            primaryImageUrl = primaryImageUrl,
            bio = bio,
            job = job,
            favoriteMovies = favoriteMovies?.map { it.toDomain() },
            preferredGenres = preferredGenres?.map { it.toDomain() }
        )
    }
}

/**
 * Location Entity
 */
data class LocationEntity(
    @SerializedName("city")
    val city: String,

    @SerializedName("district")
    val district: String
) {
    fun toDomain(): Location {
        return Location(
            city = city,
            district = district
        )
    }
}

/**
 * Movie Entity
 */
data class MovieEntity(
    @SerializedName("movieId")
    val movieId: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("posterPath")
    val posterPath: String?
) {
    fun toDomain(): Movie {
        return Movie(
            movieId = movieId,
            title = title,
            posterPath = posterPath
        )
    }
}

/**
 * Genre Entity
 */
data class GenreEntity(
    @SerializedName("genreId")
    val genreId: Long,

    @SerializedName("name")
    val name: String
) {
    fun toDomain(): Genre {
        return Genre(
            genreId = genreId,
            name = name
        )
    }
}

/**
 * Extension function to convert domain User to UserEntity
 */
fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        nickname = nickname,
        age = null, // TODO: Calculate from birthDate if needed
        gender = gender?.name,
        activityLocations = activityLocations?.map { it.toEntity() },
        profileImages = profileImages,
        primaryImageUrl = primaryImageUrl,
        bio = bio,
        job = job,
        favoriteMovies = favoriteMovies?.map { it.toEntity() },
        preferredGenres = preferredGenres?.map { it.toEntity() },
        provider = provider.name,
        birthDate = birthDate
    )
}

/**
 * Extension functions for nested entities
 */
fun Location.toEntity(): LocationEntity {
    return LocationEntity(city = city, district = district)
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(movieId = movieId, title = title, posterPath = posterPath)
}

fun Genre.toEntity(): GenreEntity {
    return GenreEntity(genreId = genreId, name = name)
}