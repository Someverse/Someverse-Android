package com.someverse.presentation.navigation

/**
 * Navigation Screens
 * - Defines all navigation routes in the app
 */
sealed class Screen(val route: String) {

    // Auth
    data object Login : Screen("login")

    // Onboarding Screens
    data object SignupLocation : Screen("signup_location")
    data object SignupProfileImage : Screen("signup_profile_image")
    data object SignupMovieCategory: Screen("signup_movie_category")
    data object SignupMovieTaste : Screen("signup_movie_taste")

    // Main
    data object Home : Screen("home")
    data object Profile : Screen("profile")
}
