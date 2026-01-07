package com.jesen.androidcomposetalk.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jesen.androidcomposetalk.MainActivity
import com.jesen.androidcomposetalk.pages.*

/**
 * 定义路由
 * */
object PageRoute {
    const val LOGIN_ROUTE = "login_route"
    const val REGISTER_ROUTE = "register_route"
    const val VIDEO_DETAIL_ROUTE = "video_detail_route"
    const val MAIN_PAGE = "main_route"
}

/**
 * 将页面与路由关联
 * */
@ExperimentalPagerApi
@Composable
fun PageNavHost(mainActivity: MainActivity) {
    val navHostController = MainActivity.pageNavController!!
    val isLogined = true // 是否登录

    // 初始页面
    val initRoute = if (isLogined) PageRoute.MAIN_PAGE else PageRoute.LOGIN_ROUTE
    NavHost(navController = navHostController, startDestination = initRoute) {
        composable(route = PageRoute.LOGIN_ROUTE) {
            LoginPage(activity = mainActivity)
        }
        composable(route = PageRoute.REGISTER_ROUTE) {
            RegisterPage(activity = mainActivity)
        }
        composable(route = PageRoute.VIDEO_DETAIL_ROUTE) {
            VideoDetailPage()
        }
        composable(route = PageRoute.MAIN_PAGE) {
            MainPage()
        }
    }
}

/**
 * 页面跳转
 * */
fun doPageNavigationTo(route: String) {
    val navController = MainActivity.pageNavController!!
    navController.navigate(route) {
        launchSingleTop = false
        popUpTo(navController.graph.findStartDestination().id) {
            // 防止状态丢失
            saveState = true
        }
        // 恢复composeble的状态
        restoreState = true
    }
}

/**
 * 页面回退
 * */
fun doPageNavBack(route: String?) {
    val navController = MainActivity.pageNavController!!
    route?.let {
        navController.popBackStack(route = it, inclusive = false)
    } ?: navController.popBackStack()
}