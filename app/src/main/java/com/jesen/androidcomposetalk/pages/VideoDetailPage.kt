package com.jesen.androidcomposetalk.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun VideoDetailPage() {
    @Suppress("UnusedMaterialScaffoldPaddingParameter")
    Scaffold() { _ ->
        Box(modifier = Modifier.fillMaxSize()) {
            Text("视频详情页呀")
        }
    }
}