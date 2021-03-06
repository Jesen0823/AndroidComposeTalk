package com.jesen.androidcomposetalk.nav

import androidx.annotation.DrawableRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jesen.androidcomposetalk.R
import com.jesen.androidcomposetalk.pages.mainchild.FavoritePage
import com.jesen.androidcomposetalk.pages.mainchild.HomeTabPage
import com.jesen.androidcomposetalk.pages.mainchild.ProfilePage
import com.jesen.androidcomposetalk.pages.mainchild.RankingPage
import com.jesen.androidcomposetalk.ui.theme.primaryDeepColor


sealed class Screens(val title: String, val route: String, @DrawableRes val icons: Int) {

    object Home : Screens(title = "首页", route = "home_route", icons = R.drawable.ic_twotone_home_24)

    object Ranking :
        Screens(title = "排行", route = "ranking_route", icons = R.drawable.ic_twotone_sort_24)

    object Favorite :
        Screens(title = "收藏", route = "fav_route", icons = R.drawable.ic_twotone_favorite_24)

    object Profile :
        Screens(title = "我的", route = "profile_route", icons = R.drawable.ic_twotone_person_24)
}

@ExperimentalPagerApi
@Composable
fun BottomNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeTabPage()
        }
        composable(route = Screens.Ranking.route) {
            RankingPage()
        }
        composable(route = Screens.Favorite.route) {
            FavoritePage()
        }
        composable(route = Screens.Profile.route) {
            ProfilePage()
        }
    }
}

@Composable
fun BottomNavigationScreen(navController: NavController, items: List<Screens>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val destination = navBackStackEntry?.destination

    BottomNavigation() {
        items.forEach { screens ->
            BottomNavigationItem(
                selected = destination?.route == screens.route,
                onClick = {
                    navController.navigate(screens.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            // 防止状态丢失
                            saveState = true
                        }
                        // 恢复composeble的状态
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screens.icons),
                        contentDescription = null
                    )
                },
                label = { Text(screens.title) },
                alwaysShowLabel = true,
                selectedContentColor = primaryDeepColor,
            )

        }
    }
}