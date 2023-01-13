package com.tvmaze.challenge.domain.sources.remote

import com.tvmaze.challenge.remote.models.ShowEpisodeModel
import com.tvmaze.challenge.remote.models.ShowSearchModel
import com.tvmaze.challenge.remote.models.TVShowModel
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface TVMazeRemoteSource {
     suspend fun getAllShows(): Response<List<TVShowModel>>
     suspend fun getPaginatedShows(page: Int): Response<List<TVShowModel>>
     suspend fun getShowsByName(name: String): Response<List<ShowSearchModel>>
     suspend fun getShowDetail(id: Int): Response<TVShowModel>
     suspend fun getEpisodesByShowId(id: Int): Response<List<ShowEpisodeModel>>
}