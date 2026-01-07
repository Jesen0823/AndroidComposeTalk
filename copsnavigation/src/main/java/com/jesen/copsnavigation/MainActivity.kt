package com.jesen.copsnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.jesen.copsnavigation.lazycolumn.LazyColumnDemo
import com.jesen.copsnavigation.splash.NavigationSplash
import com.jesen.copsnavigation.ui.theme.AndroidComposeTalkTheme

class MainActivity : ComponentActivity() {

    //private val currentRoute = mutableStateOf(Screen.Home.route)

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeTalkTheme {

                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    // 3.0
                    //LazyColumnDemo()

                    // 2.0
                    NavigationSplash()

                    // 1.0
                    /*Scaffold(
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
                    }*/
                }
            }
        }
    }
}
