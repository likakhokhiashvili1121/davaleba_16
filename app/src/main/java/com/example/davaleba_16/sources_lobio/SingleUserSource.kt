package com.example.davaleba_16.sources_lobio

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.davaleba_16.model.RetrofitObject
import com.example.davaleba_16.model.UsersData

class SingleUserSource (
    private val userRepository: RetrofitObject.UserRepository
) : PagingSource<Int, UsersData.Data>() {

    override fun getRefreshKey(state: PagingState<Int, UsersData.Data>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UsersData.Data> {

        val page: Int = params.key ?: 1

        userRepository.getInfo(page)?.let {
            return LoadResult.Page(
                it.data ?: emptyList(),
                if (page > 1) page - 1 else null,
                if (page < it.totalPages!!) page + 1 else null
            )
        }

        return LoadResult.Error(Exception())

    }
}