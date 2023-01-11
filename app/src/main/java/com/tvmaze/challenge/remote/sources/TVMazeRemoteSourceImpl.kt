package com.tvmaze.challenge.remote.sources

import com.tvmaze.challenge.domain.sources.remote.TVMazeRemoteSource
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.remote.services.TvMazeService
import retrofit2.Response
import javax.inject.Inject

class TVMazeRemoteSourceImpl @Inject constructor(
    private val service: TvMazeService
): TVMazeRemoteSource {
    override suspend fun getAllShows(): Response<List<TVShowModel>> = service.getAllShows()

    override suspend fun getPaginatedShows(page: String): Response<List<TVShowModel>> = service.getPaginatedShows(page)
}