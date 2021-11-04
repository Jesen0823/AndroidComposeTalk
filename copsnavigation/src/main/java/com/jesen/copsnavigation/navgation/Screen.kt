package com.jesen.copsnavigation.navgation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.jesen.copsnavigation.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    object Home : Screen("home", R.string.home, Icons.Rounded.Home)
    object Favorite : Screen("favorite", R.string.favorite, Icons.Rounded.Favorite)
    object Profile : Screen("profile", R.string.profile, Icons.Rounded.Person)
}