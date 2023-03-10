package com.tvmaze.challenge.remote.services

import com.tvmaze.challenge.remote.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeService {
    @GET("shows")
    suspend fun getAllShows(): Response<List<TVShowModel>>

    @GET("shows")
    suspend fun getPaginatedShows(@Query("page") page: Int): Response<List<TVShowModel>>

    @GET("search/shows")
    suspend fun getShowsByName(@Query("q") name: String): Response<List<ShowSearchModel>>

    @GET("shows/{id}")
    suspend fun getShowDetail(@Path("id") id: Int): Response<TVShowModel>

    @GET("shows/{id}/images")
    suspend fun getShowImages(@Path("id") id: Int): Response<List<ShowImagesModel>>

    @GET("shows/{id}/seasons")
    suspend fun getShowSeasons(@Path("id") id: Int): Response<List<ShowSeasonModel>>

    @GET("shows/{id}/episodes")
    suspend fun getEpisodesByShowId(@Path("id") id: Int): Response<List<ShowEpisodeModel>>

    @GET("seasons/{id}/episodes")
    suspend fun getSeasonEpisodes(@Path("id") id: Int): Response<List<ShowEpisodeModel>>
}