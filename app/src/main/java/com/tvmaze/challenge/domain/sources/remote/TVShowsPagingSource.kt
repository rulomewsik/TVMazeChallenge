package com.tvmaze.challenge.domain.sources.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tvmaze.challenge.domain.usecases.TVMazeUseCase
import com.tvmaze.challenge.remote.models.TVShowModel
import javax.inject.Inject

class TVShowsPagingSource @Inject constructor(
    private val tvMazeUseCase: TVMazeUseCase
) : PagingSource<Int, TVShowModel>() {
    override fun getRefreshKey(state: PagingState<Int, TVShowModel>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShowModel> {
        return try {
            val page = params.key ?: 0
            val response = tvMazeUseCase.getPaginatedShows(page)

            LoadResult.Page(
                data = response!!,
                prevKey = if (page == 0) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}