package com.jesen.androidcomposetalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.jesen.androidcomposetalk.nav.PageNavHost
import com.jesen.androidcomposetalk.pages.MainPage
import com.jesen.androidcomposetalk.ui.theme.AndroidComposeTalkTheme
import com.jesen.androidcomposetalk.util.ColorUtil

/**
 *  Home page
 * */
class MainActivity : ComponentActivity() {

    companion object{
        var pageNavController: NavHostController? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            pageNavController = rememberNavController()
            AndroidComposeTalkTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    @Suppress("UnusedMaterialScaffoldPaddingParameter")
                    Scaffold() { _ ->
                        PageNavHost(this)
                    }
                }
            }
        }
    }
}
