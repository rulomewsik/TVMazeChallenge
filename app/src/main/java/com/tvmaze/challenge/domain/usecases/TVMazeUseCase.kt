package com.tvmaze.challenge.domain.usecases

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tvmaze.challenge.domain.sources.remote.TVMazeRemoteSource
import com.tvmaze.challenge.domain.sources.remote.TVShowsPagingSource
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.remote.sources.TVMazeRemoteSourceImpl
import kotlinx.coroutines.delay
import javax.inject.Inject

class TVMazeUseCase @Inject constructor(
    private val tvMazeRemoteSource: TVMazeRemoteSource
) {
    suspend fun getAllTVShows(): List<TVShowModel>? {
        val response = tvMazeRemoteSource.getAllShows()

        if (response.isSuccessful) {
            response.body().let {
                return it
            }
        } else {
            return emptyList()
        }
    }

    suspend fun getPaginatedShows(page: Int): List<TVShowModel>? {
        val response = tvMazeRemoteSource.getPaginatedShows(page.toString())

        Log.d("Holi", "Page $page")
        if (response.isSuccessful) {
            response.body().let {
                Log.d("Holi", "Response size " + it?.size)
                return it
            }
        } else {
            return emptyList()
        }
    }

    fun getShows() = Pager(
        config = PagingConfig(
            initialLoadSize = 250,
            pageSize = 250,
            prefetchDistance = 25
        ),
        pagingSourceFactory = {
            TVShowsPagingSource(this)
        }
    ).flow
}