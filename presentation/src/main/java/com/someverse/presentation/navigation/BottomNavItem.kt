package com.someverse.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.someverse.presentation.R

/**
 * Bottom Navigation Items
 * - Defines tab bar items and their properties
 */
sealed class BottomNavItem(
    val route: String,
    @param:StringRes val titleRes: Int,
    @param:DrawableRes val icon: Int,
) {
    data object MyProfile : BottomNavItem(
        route = Screen.MyProfile.route,
        titleRes = R.string.my,
        icon = R.drawable.ic_my_profile,
    )

    data object Feed : BottomNavItem(
        route = Screen.Feed.route,
        titleRes = R.string.feed,
        icon = R.drawable.ic_feed,
    )

    data object Matching : BottomNavItem(
        route = Screen.Matching.route,
        titleRes = R.string.matching,
        icon = R.drawable.ic_match,
    )

    data object Chat : BottomNavItem(
        route = Screen.Chat.route,
        titleRes = R.string.chat_title,
        icon = R.drawable.ic_chat,
    )

    companion object {
        val items =
            listOf(
                Matching,
                Feed,
                Chat,
                MyProfile,
            )
    }
}
