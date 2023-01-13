package com.tvmaze.challenge.domain.sources.remote

import com.tvmaze.challenge.remote.models.ShowSearchModel
import com.tvmaze.challenge.remote.models.TVShowModel
import retrofit2.Response
import retrofit2.http.Query

interface TVMazeRemoteSource {
     suspend fun getAllShows(): Response<List<TVShowModel>>
     suspend fun getPaginatedShows(page: String): Response<List<TVShowModel>>
     suspend fun getShowsByName(name: String): Response<List<ShowSearchModel>>
}