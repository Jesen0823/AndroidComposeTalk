package com.jesen.androidcomposetalk.pages

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

import com.jesen.androidcomposetalk.nav.BottomNavHost
import com.jesen.androidcomposetalk.nav.BottomNavigationScreen
import com.jesen.androidcomposetalk.nav.Screens

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
        BottomNavHost(navHostController = navController, padding = it)
    }
}