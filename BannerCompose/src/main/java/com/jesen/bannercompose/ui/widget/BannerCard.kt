package com.jesen.bannercompose.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.jesen.bannercompose.banner.ImageLoader
import com.jesen.bannercompose.model.BannerBean

/**
 * Banner 图片展示卡片
 *
 * @param bean banner Model
 * @param modifier
 * @param shape 图片圆角
 * @param contentScale 纵横比缩放
 * @param onBannerClick Banner 图片点击事件
 */
@Composable
fun <T : BannerBean> BannerCard(
    bean: T,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(10.dp),
    contentScale: ContentScale,
    onBannerClick: () -> Unit,
) {
    if (bean.data == null) {
        throw NullPointerException("Url or imgRes or filePath must have a not for empty.")
    }

    Card(
        shape = shape,
        modifier = modifier
    ) {
        val imgModifier = Modifier.clickable(onClick = onBannerClick)
        ImageLoader(bean.data, imgModifier, contentScale)
    }
}
