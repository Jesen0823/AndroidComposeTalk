package com.jesen.paging3demo

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState


// 分页数据处理
class QuestionPagingSource(private val apiService: RetrofitService) :
    PagingSource<Int, Question>() {

    override fun getRefreshKey(state: PagingState<Int, Question>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Question> {
        Log.d("QuestionPagingSource", "load--")
        try {
            val pageIndex: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.getDataFromApi(pageIndex)
            Log.d("QuestionPagingSource", "load--， pageIndex：$pageIndex")
            Log.d("QuestionPagingSource", "load--， response：$response")
            val responseList = mutableListOf<Question>()
            val data = response.result?.resultData?.questionList ?: emptyList<Question>()
            responseList.addAll(data)
            val prevKey = if (pageIndex == 1) null else pageIndex.minus(1)
            val nextKey = pageIndex.plus(1)

            return LoadResult.Page(data = responseList, prevKey = prevKey, nextKey = nextKey)

        } catch (e: Exception) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

    companion object {
        //加载页码
        var FIRST_PAGE_INDEX = 1
    }
}