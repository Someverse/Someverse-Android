package com.someverse.domain.model

/**
 * Movie Model
 */
data class Movie(
    val movieId: Long,
    val title: String,
    val posterPath: String?,
)
