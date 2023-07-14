package com.tinne14.testprojectsuitmedia.ui.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.tinne14.testprojectsuitmedia.UserPaggingSource
import com.tinne14.testprojectsuitmedia.data.ApiService
import com.tinne14.testprojectsuitmedia.data.DataItem

class ThirdScreenViewmodel(private val apiService: ApiService) : ViewModel() {

    fun getUser(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6
            ),
            pagingSourceFactory = {
                UserPaggingSource(apiService)
            }
        ).liveData.cachedIn(viewModelScope)
    }
}