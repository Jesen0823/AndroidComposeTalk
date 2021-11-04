package com.jesen.androidcomposetalk.pages.mainchild

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * 首页 HorizontalPager 和 TabRow的使用
 * ScrollableTabRow: 可滑动的 TabView
 *
 * implement 'com.google.accompanist:accompanist-pager:0.18.0'
 *
 * */

@ExperimentalPagerApi
@Composable
fun HomeTabPage(){
    val  items = listOf("推荐", "电影", "电视剧", "综艺","纪录片","娱乐","娱乐")
    Surface(color = MaterialTheme.colors.background) {

        val tabstr = remember {
            mutableStateOf(items[0])
        }

        val scope = rememberCoroutineScope()

        val state  = rememberPagerState(
            pageCount = items.size, //总页数
            initialOffscreenLimit = 3, //预加载的个数
            infiniteLoop = false, //是否无限循环
            initialPage = 0 //初始页面
        )
        /*val state = rememberPagerState(
            //总页数
            pageCount = items.size,
        )*/

        Column(modifier = Modifier.fillMaxSize()) {
            Column {

                // 固定长度的TabView
                TabRow(
                    selectedTabIndex = items.indexOf(tabstr.value),
                    modifier = Modifier.fillMaxWidth(),
                    indicator = { tabIndicator ->
                        TabRowDefaults.Indicator(
                            Modifier.tabIndicatorOffset(
                                tabIndicator[items.indexOf(
                                    tabstr.value
                                )]
                            )
                        )
                    },
                    divider = {}
                ) {
                    items.forEachIndexed { index, title ->
                        val selected = index == items.indexOf(tabstr.value)
                        Tab(
                            modifier = Modifier.background(color = Color.Gray),
                            selected = selected,
                            onClick = {
                                tabstr.value = items[index]
                                scope.launch {
                                    state.scrollToPage(index)
                                }
                            },
                            text = { Text(text = items[index]) },
                            selectedContentColor = Color.Blue,
                            unselectedContentColor = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                // 可滑动TabView
                ScrollableTabRow(
                    selectedTabIndex = items.indexOf(tabstr.value),
                    modifier = Modifier.wrapContentWidth(),
                    edgePadding = 16.dp,
                    indicator = { tabIndicator ->
                        TabRowDefaults.Indicator(
                            Modifier.tabIndicatorOffset(
                                tabIndicator[items.indexOf(
                                    tabstr.value
                                )]
                            ),
                            color = Color.Cyan
                        )
                    },
                    divider = {}
                ) {
                    items.forEachIndexed { index, title ->
                        val selected = index == items.indexOf(tabstr.value)
                        Tab(
                            modifier = Modifier.background(color = Color.White),
                            text = { Text(title) },
                            selected = selected,
                            onClick = {
                                //state.value = index
                                tabstr.value = items[index]
                                scope.launch {
                                    state.scrollToPage(index)
                                }
                            }
                        )
                    }
                }

                // 横向Pager类似PagerView
                HorizontalPager(state = state) { indexPage ->
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (indexPage) {
                            in 0..(items.size)  -> Text(text = items[indexPage])

                        }
                        Text(text = items[indexPage])
                    }
                }
            }
            tabstr.value = items[state.currentPage]
        }
    }
}