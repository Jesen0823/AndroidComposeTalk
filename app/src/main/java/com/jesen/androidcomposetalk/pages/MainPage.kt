package com.jesen.androidcomposetalk.pages

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jesen.androidcomposetalk.nav.BottomNavHost
import com.jesen.androidcomposetalk.nav.BottomNavigationScreen
import com.jesen.androidcomposetalk.nav.Screens

@ExperimentalPagerApi
@Composable
fun MainPage() {
    val list = listOf(
        Screens.Home,
        Screens.Ranking,
        Screens.Favorite,
        Screens.Profile,
    )
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomNavigationScreen(navController = navController, items = list)
    }) {
        BottomNavHost(navHostController = navController)
    }
}