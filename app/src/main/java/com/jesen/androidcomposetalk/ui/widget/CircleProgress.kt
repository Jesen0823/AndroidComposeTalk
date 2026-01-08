package com.jesen.androidcomposetalk.ui.widget

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCircularProgress(
    canvasSize: Dp = 100.dp,
    indicatorValue: Float = 0f,
    backgroundIndicatorColor: Color = Color.Black,
    backgroundIndicatorStrokeWidth: Float = 4f,
    foregroundIndicatorColor: Color = Color.Black,
    foregroundIndicatorStrokeWidth: Float = 4f,
) {
    val animatedIndicatorValue = remember {
        Animatable(initialValue = 0f)
    }
    LaunchedEffect(key1 = indicatorValue, block = {
        animatedIndicatorValue.animateTo(indicatorValue.toFloat())
    })
    val sweepAngle by animateFloatAsState(
        targetValue = animatedIndicatorValue.value,
        animationSpec = tween(250)
    )

    Column(modifier = Modifier
        .size(canvasSize)
        .drawBehind {
            backgroundIndicator(
                indicatorValue,
                backgroundIndicatorColor,
                backgroundIndicatorStrokeWidth
            )
            foregroundIndicator(
                sweepAngle = sweepAngle,
                indicatorColor = foregroundIndicatorColor,
                indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
            )
        }) {
    }
}

// 前景绘制
fun DrawScope.foregroundIndicator(
    sweepAngle: Float = 0f,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        color = indicatorColor,
        startAngle = 90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        )
    )
}

// 背景绘制
fun DrawScope.backgroundIndicator(
    sweepAngle: Float = 360f,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        color = indicatorColor,
        startAngle = 90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        )
    )
}


@Preview
@Composable
fun ShowCustomCircularProgress() {
    CustomCircularProgress()
}