package com.someverse.domain.model

/**
 * User Model
 */
data class User(
    val id: String,
    val provider: SocialProvider,
    val nickname: String?,
    val birthDate: String?,
    val gender: Gender?,
    val activityLocations: List<Location>?,
    val profileImages: ProfileImages?,
    val primaryImageUrl: String?,
    val bio: String?,
    val job: String?,
    val favoriteMovies: List<Movie>?,
    val preferredGenres: List<Genre>?,
)

/**
 * Location Model
 */
data class Location(
    val city: String,
    val district: String,
)

/**
 * Genre Model
 */
data class Genre(
    val genreId: Long,
    val name: String,
)

/**
 * Profile Images Information
 */
data class ProfileImages(
    val images: List<String>,
    val primaryImageIndex: Int,
)
