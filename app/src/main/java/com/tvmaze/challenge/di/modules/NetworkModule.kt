package com.tvmaze.challenge.di.modules

import com.tvmaze.challenge.BuildConfig
import com.tvmaze.challenge.utils.Constants.TV_MAZE_BASE_URL
import com.tvmaze.challenge.remote.services.TvMazeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideTvMazeRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(TV_MAZE_BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideTvMazeService(retrofit: Retrofit): TvMazeService = retrofit.create(TvMazeService::class.java)
}