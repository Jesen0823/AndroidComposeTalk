package com.jesen.copsnavigation.customcomposable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jesen.copsnavigation.ColorBox
import com.jesen.copsnavigation.ColorBox2
import com.jesen.copsnavigation.navgation.Screen

@Composable
fun MainScreen(){
     val currentRoute = remember {
         mutableStateOf(Screen.Home.route)
     }

    Scaffold(
        bottomBar = {
            CustomBottomNavigation(
                selectedRoute = currentRoute.value,
                onItemSelected = {
                    currentRoute.value = it.route
                }
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        val commonColor = remember { mutableStateOf(Color.Blue) }

        Column(modifier = Modifier.fillMaxSize()) {
            ColorBox()
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
                    .background(commonColor.value)
            )
            ColorBox2(modifier = Modifier.background(color = commonColor.value)){
                commonColor.value = it
            }
        }
    }
}