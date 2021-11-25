package com.jesen.bannercompose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jesen.bannercompose.banner.BannerPager
import com.jesen.bannercompose.model.BannerBean
import com.jesen.bannercompose.ui.widget.indicator.BannerGravity
import com.jesen.bannercompose.ui.widget.indicator.CircleIndicator
import com.jesen.bannercompose.ui.widget.indicator.NumberIndicator

data class MyBannerBean(
    override val data: Any? = null
) : BannerBean()

@Composable
fun BannerTest() {
    val context = LocalContext.current
    val scroller = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().verticalScroll(scroller)) {


        val items = arrayListOf(
            MyBannerBean(
                "https://wanandroid.com/blogimgs/8a0131ac-05b7-4b6c-a8d0-f438678834ba.png",
            ),
            MyBannerBean(
                "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
            ),
            MyBannerBean(
                "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
            ),
            MyBannerBean(
                "https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png",
            ),
        )

        BannerPager(
            items = items,
        ) { item ->
            Toast.makeText(context, "item:$item", Toast.LENGTH_SHORT).show()
        }


        val items2 = arrayListOf(
            MyBannerBean(R.drawable.bbtt1),
            MyBannerBean(R.drawable.bbtt2),
            MyBannerBean(R.drawable.bbtt3),
            MyBannerBean(R.drawable.bbtt4),
        )

        BannerPager(
            modifier = Modifier.padding(top = 10.dp),
            items = items2,
            indicator = CircleIndicator(gravity = BannerGravity.BottomLeft)
        ) { item ->
            Toast.makeText(context, "item:$item", Toast.LENGTH_SHORT).show()
        }


        BannerPager(
            modifier = Modifier.padding(top = 10.dp),
            items = items,
            indicator = NumberIndicator()
        ) { item ->
            Toast.makeText(context, "item:$item", Toast.LENGTH_SHORT).show()
        }


        BannerPager(
            modifier = Modifier.padding(top = 10.dp),
            items = items2,
            indicator = NumberIndicator(gravity = BannerGravity.BottomLeft)
        ) { item ->
            Toast.makeText(context, "item:$item", Toast.LENGTH_SHORT).show()
        }

    }
}