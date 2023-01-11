package com.tvmaze.challenge.domain.sources.remote

import com.tvmaze.challenge.remote.models.TVShowModel
import retrofit2.Response

interface TVMazeRemoteSource {
     suspend fun getAllShows(): Response<List<TVShowModel>>
     suspend fun getPaginatedShows(page: String): Response<List<TVShowModel>>
}