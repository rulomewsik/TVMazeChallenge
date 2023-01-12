package com.tvmaze.challenge.domain.usecases

import com.tvmaze.challenge.domain.sources.local.FavoriteShowsSource
import javax.inject.Inject

class FavoriteShowsUseCase @Inject constructor(
    private val favoriteShowsSource: FavoriteShowsSource
) {
    fun getFavoriteShows() = favoriteShowsSource.getFavoriteShows()
}