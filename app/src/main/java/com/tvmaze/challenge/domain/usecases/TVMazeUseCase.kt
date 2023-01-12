package com.tvmaze.challenge.domain.usecases

import com.tvmaze.challenge.domain.sources.remote.TVMazeRemoteSource
import com.tvmaze.challenge.remote.models.TVShowModel
import javax.inject.Inject

class TVMazeUseCase @Inject constructor(
    private val tvMazeRemoteSource: TVMazeRemoteSource
) {
    suspend fun getAllTVShows(): List<TVShowModel>? {
        val response = tvMazeRemoteSource.getAllShows()

        if (response.isSuccessful){
            response.body().let {
                return it
            }
        }else {
            return emptyList()
        }
    }

    suspend fun getPaginatedShows(page: Int): List<TVShowModel>? {
        val response = tvMazeRemoteSource.getPaginatedShows(page.toString())

        if (response.isSuccessful){
            response.body().let {
                return it
            }
        }else {
            return emptyList()
        }
    }
}