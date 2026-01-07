package com.jesen.androidcomposetalk

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesen.androidcomposetalk.ui.theme.AndroidComposeTalkTheme
import com.jesen.androidcomposetalk.ui.theme.bili_120
import com.jesen.androidcomposetalk.ui.theme.bili_20
import com.jesen.androidcomposetalk.ui.theme.bili_90
import com.jesen.androidcomposetalk.ui.widget.VideoItemCard
import com.jesen.androidcomposetalk.util.ColorUtil
import com.jesen.androidcomposetalk.util.oLog

class TestActivity : ComponentActivity() {

    private val listStr =
        listOf("2222", "2223", "2444", "3566", "4458", "2225", "2223", "2744", "0566", "4245")

    @RequiresApi(Build.VERSION_CODES.O)
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeTalkTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    // Greeting("Android")
                    VideoItemCard()
                }
                /*Column(Modifier.fillMaxWidth()) {
                    MessageList(messages = listStr)
                }*/
            }
        }
    }

    @Preview
    @Composable
    fun CanvasTest() {
        Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
            drawLine(
                start = Offset(0f, 0f),
                end = Offset(size.width, size.height),
                color = Color.Blue,
                strokeWidth = 5f
            )
            rotate(degrees = 45f) {
                drawRect(
                    color = Color.Green,
                    size = size / 4f,
                    topLeft = Offset(size.width / 3f, size.height / 3f)
                )
            }
            drawCircle(
                color = Color.Blue,
                center = Offset(size.width / 2, size.height / 2),
                radius = 50f
            )
            //多个状态组合 旋转和平移
            withTransform({
                translate(left = size.width / 5f)
                rotate(degrees = 45f)
            }) {
                drawRect(
                    color = Color.Yellow,
                    size = size / 5f,
                    topLeft = Offset(size.width / 3f, size.height / 3f)
                )
            }
        })
    }

}

@ExperimentalFoundationApi
@Composable
fun MessageList(messages: List<String>) {
    val listState = rememberLazyListState()
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp), state = listState
    ) {
        println("滚动：${listState.firstVisibleItemIndex}==${listState.firstVisibleItemScrollOffset}")

        stickyHeader(1) {
            Text(
                text = "我是头部",
                Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Green)
            )
        }
        itemsIndexed(messages) { index, message ->
            Text(
                text = "$message===$index",
                Modifier
                    .background(Color.Yellow)
                    .height(60.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Color.Gray)
            )
        }
    }
}
