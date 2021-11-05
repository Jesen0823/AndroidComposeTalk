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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jesen.androidcomposetalk.nav.PageNavHost
import com.jesen.androidcomposetalk.ui.theme.AndroidComposeTalkTheme

/**
 *  Home page
 * */
class MainActivity : ComponentActivity() {

    companion object{
        var pageNavController: NavHostController? = null
    }

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            pageNavController = rememberNavController()

            AndroidComposeTalkTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold() {
                        PageNavHost(this)
                    }
                }
            }
        }
    }
}
