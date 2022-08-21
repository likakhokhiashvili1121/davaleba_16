package com.example.davaleba_16.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.davaleba_16.model.RetrofitObject
import com.example.davaleba_16.sources_lobio.SingleUserSource

class MainViewModel: ViewModel() {

    fun getUserInfo() = Pager(
        config = PagingConfig(pageSize = 1),
        pagingSourceFactory = { SingleUserSource(RetrofitObject.UserRepository()) }
    ).flow

}