package com.tvmaze.challenge.di.modules

import com.tvmaze.challenge.domain.sources.local.ShowsListSource
import com.tvmaze.challenge.domain.sources.remote.TVMazeRemoteSource
import com.tvmaze.challenge.local.sources.ShowsListSourceImpl
import com.tvmaze.challenge.remote.sources.TVMazeRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideTvMazeRemoteSource(tvMazeRemoteSource: TVMazeRemoteSourceImpl): TVMazeRemoteSource = tvMazeRemoteSource

    @Singleton
    @Provides
    fun provideShowsListLocalSource(showsListSource: ShowsListSourceImpl): ShowsListSource = showsListSource
}