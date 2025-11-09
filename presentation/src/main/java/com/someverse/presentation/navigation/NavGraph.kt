package com.someverse.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.someverse.presentation.ui.auth.login.LoginScreen
import com.someverse.presentation.ui.auth.signup.SignupLocationScreen
import com.someverse.presentation.ui.auth.signup.SignupMovieCategoryScreen
import com.someverse.presentation.ui.auth.signup.SignupMovieTasteScreen
import com.someverse.presentation.ui.auth.signup.SignupProfileImageScreen

/**
 * App Navigation Graph
 * - Defines navigation structure
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Login
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // 로그인 성공 시 닉네임 화면으로 이동 (온보딩 첫 단계)
                    navController.navigate(Screen.SignupLocation.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // 4. Location
        composable(route = Screen.SignupLocation.route) {
            SignupLocationScreen(
                onNext = {
                    navController.navigate(Screen.SignupProfileImage.route)
                }
            )
        }

        // 5. Profile Image
        composable(route = Screen.SignupProfileImage.route) {
            SignupProfileImageScreen(
                onNext = {
                    navController.navigate(Screen.SignupMovieCategory.route)
                }
            )
        }

        // 6. Movie Category
        composable(route = Screen.SignupMovieCategory.route) {
            SignupMovieCategoryScreen(
                onNext = {
                    navController.navigate(Screen.SignupMovieTaste.route)
                }
            )
        }

        // 7. Movie Taste
        composable(route = Screen.SignupMovieTaste.route) {
            SignupMovieTasteScreen(
                onNext = {
                    // 온보딩 완료 시 홈 화면으로 이동
                    navController.navigate(Screen.Home.route) {
                        // 이전 화면들 모두 제거
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // Home
        composable(route = Screen.Home.route) {
            // HomeScreen()
        }

        // Profile
        composable(route = Screen.Profile.route) {
            // ProfileScreen()
        }
    }
}