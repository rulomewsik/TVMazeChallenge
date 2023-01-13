package com.tvmaze.challenge.remote.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tvmaze.challenge.domain.sources.remote.TVMazeRemoteSource
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.remote.services.TvMazeService
import retrofit2.Response
import javax.inject.Inject

class TVMazeRemoteSourceImpl @Inject constructor(
    private val service: TvMazeService
): TVMazeRemoteSource, PagingSource<Int, TVShowModel>() {
    override suspend fun getAllShows(): Response<List<TVShowModel>> = service.getAllShows()

    override suspend fun getPaginatedShows(page: String): Response<List<TVShowModel>> = service.getPaginatedShows(page)

    override fun getRefreshKey(state: PagingState<Int, TVShowModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShowModel> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = service.getPaginatedShows(nextPageNumber.toString())

            LoadResult.Page(
                data = response.body()!!,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber.minus(1),
                nextKey = if (response.body()!!.isEmpty()) null else nextPageNumber.plus(1)
            )
        } catch (e: java.lang.Exception) {
            LoadResult.Error(e)
        }
    }
}