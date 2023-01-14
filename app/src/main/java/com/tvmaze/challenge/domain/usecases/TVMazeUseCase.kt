package com.tvmaze.challenge.domain.usecases

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tvmaze.challenge.domain.sources.remote.TVMazeRemoteSource
import com.tvmaze.challenge.domain.sources.remote.TVShowsPagingSource
import com.tvmaze.challenge.remote.models.*
import retrofit2.Response
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

    fun getShowsPager() = Pager(
        config = PagingConfig(
            initialLoadSize = 250,
            pageSize = 250,
            prefetchDistance = 25
        ),
        pagingSourceFactory = {
            TVShowsPagingSource(this)
        }
    ).flow

    suspend fun getPaginatedShows(page: Int): List<TVShowModel>? {
        val response = tvMazeRemoteSource.getPaginatedShows(page)

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

    suspend fun getShowsByName(name: String): List<ShowSearchModel>? {
        val response = tvMazeRemoteSource.getShowsByName(name)

        if (response.isSuccessful) {
            response.body().let {
                return it
            }
        } else {
            return emptyList()
        }
    }

    suspend fun getShowDetail(id: Int): TVShowModel? {
        val response = tvMazeRemoteSource.getShowDetail(id)

        if (response.isSuccessful) {
            response.body().let {
                return it
            }
        } else {
            return TVShowModel()
        }
    }

    suspend fun getShowImages(id: Int): List<ShowImagesModel>? {
        val response = tvMazeRemoteSource.getShowImages(id)

        if (response.isSuccessful) {
            response.body().let {
                return it
            }
        } else {
            return emptyList()
        }
    }

    suspend fun getShowSeasons(id: Int): List<ShowSeasonModel>? {
        val response = tvMazeRemoteSource.getShowSeasons(id)

        if (response.isSuccessful) {
            response.body().let {
                return it
            }
        } else {
            return emptyList()
        }
    }

    suspend fun getEpisodesByShowId(id: Int): List<ShowEpisodeModel>? {
        val response = tvMazeRemoteSource.getEpisodesByShowId(id)

        if (response.isSuccessful) {
            response.body().let {
                return it
            }
        } else {
            return emptyList()
        }
    }

    suspend fun getSeasonEpisodes(id: Int): List<ShowEpisodeModel>? {
        val response = tvMazeRemoteSource.getSeasonEpisodes(id)

        if (response.isSuccessful) {
            response.body().let {
                return it
            }
        } else {
            return emptyList()
        }
    }
}