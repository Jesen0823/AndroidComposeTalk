package com.jesen.paging3demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    var retrofitService: RetrofitService = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(pageSize = 43, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(retrofitService) }).flow.cachedIn(
            viewModelScope
        )
    }
}