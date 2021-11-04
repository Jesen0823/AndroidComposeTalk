package com.jesen.androidcomposetalk.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jesen.androidcomposetalk.nav.BottomNavHost
import com.jesen.androidcomposetalk.nav.BottomNavigationScreen
import com.jesen.androidcomposetalk.nav.Screens

@ExperimentalPagerApi
@Composable
fun MainPage(){
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