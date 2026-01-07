package com.jesen.copsnavigation.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.jesen.copsnavigation.R
import kotlinx.coroutines.delay

@Composable
fun NavigationSplash(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen" ){

        composable("splash_screen"){
            SplashScreen(navController = navController)

        }

        composable("main_screen"){
            Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                Text(text="Main Screen")
            }

        }
    }
}

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        Animatable(0f)
    }
    navController.enableOnBackPressed(false)
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 1.0f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
            )
        delay(3000L)
        navController.navigate("main_screen") {
            launchSingleTop = false
            popUpTo(navController.graph.findStartDestination().id){
                // 防止状态丢失
                saveState = false
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.splash),modifier = Modifier.scale(scale.value).fillMaxSize(), contentDescription = null)

    }
}