package com.jesen.androidcomposetalk.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.OndemandVideo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.google.android.material.button.MaterialButton
import com.jesen.androidcomposetalk.ui.theme.black87
import com.jesen.androidcomposetalk.ui.theme.gray300
import com.jesen.androidcomposetalk.ui.theme.gray850
import com.jesen.androidcomposetalk.util.CoilCircleImage
import com.jesen.androidcomposetalk.util.CoilImage
import com.jesen.androidcomposetalk.util.countFormat

/**
 * 视频Item卡片
 * */
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun VideoItemCard() {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp),
        shape = RoundedCornerShape(6.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface,
        onClick = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
        ) {

            CoilImage(
                url = "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png",
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
            )
            Text(
                modifier = Modifier.padding(vertical = 5.dp, horizontal = 8.dp),
                text = "会是差多吃点 吃大餐 从重试次数是市场上四川省菜市场四川省菜市场ffffffffklllllllhhh简化公共过过滚滚滚",
                style = TextStyle(
                    color = black87,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            // 互动小图标
            VideoInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
            )
            // 作者信息
            AuthorInfo(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                authImg = "http://img3.doubanio.com/view/group_topic/l/public/p314207052.jpg",
                authName = "蝴蝶姐姐"
            )
        }
    }
}

@Composable
fun VideoInfo(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(36.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconTextSmall(Icons.Rounded.OndemandVideo, 20000)
        IconTextSmall(Icons.Rounded.FavoriteBorder, 101)
    }
}


@Composable
fun IconTextSmall(imageVector: ImageVector, count: Int, modifier: Modifier? = null) {
    Row(
        modifier = modifier ?: Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
            tint = gray850
        )
        Text(
            text = countFormat(count),
            modifier = Modifier.padding(start = 6.dp),
            style = TextStyle(fontSize = 12.sp, color = Color.Gray)
        )
    }
}

@ExperimentalCoilApi
@Composable
fun AuthorInfo(modifier: Modifier, authImg: String, authName: String) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        CoilCircleImage(
            url = authImg, modifier = Modifier
                .size(80.dp)
                .padding(start = 24.dp, end = 8.dp)
        )
        Text(
            modifier = Modifier.padding(start = 18.dp),
            text = authName,
            style = TextStyle(fontSize = 18.sp, color = black87)
        )
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 16.dp),
            tint = black87
        )
    }
}
