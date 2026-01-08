package com.jesen.androidcomposetalk.pages.mainchild

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProfilePage(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize().then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "profile")
    }
}