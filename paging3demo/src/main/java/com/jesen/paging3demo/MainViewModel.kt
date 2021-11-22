package com.jesen.paging3demo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val retrofitService: RetrofitService) : ViewModel() {


    fun getListData(): Flow<PagingData<Question>> {
        Log.d("MainViewModel", "getListData")
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                QuestionPagingSource(retrofitService)
            }
        ).flow.cachedIn(
            viewModelScope
        )
    }
}