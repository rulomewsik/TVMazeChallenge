package com.tvmaze.challenge.remote.services

import com.tvmaze.challenge.remote.models.TVShowModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeService {
    @GET("shows")
    suspend fun getAllShows(): Response<List<TVShowModel>>

    @GET("shows")
    suspend fun getPaginatedShows(@Query("page") page: String): Response<List<TVShowModel>>
}