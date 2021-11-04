package com.jesen.copsnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

// 示例：状态更新，自己管理状态
@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    val color = remember {
        mutableStateOf(Color.Yellow)
    }
    Box(modifier = Modifier
        .background(color.value)
        .height(200.dp)
        .width(400.dp)
        .clickable {
            color.value = Color(
                Random.nextFloat(),
                Random.nextFloat(),
                Random.nextFloat(),
                1f
            )
        }
    )
}

// 不自己管理状态
@Composable
fun ColorBox2(
    modifier: Modifier = Modifier,
    myUpdateColor: (Color) -> Unit
) {
    Box(modifier = Modifier
        .height(100.dp)
        .width(460.dp)
        .clickable {
            myUpdateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        }
    )
}