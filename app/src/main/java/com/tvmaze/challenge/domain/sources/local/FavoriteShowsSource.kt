package com.tvmaze.challenge.domain.sources.local

import com.tvmaze.challenge.domain.entities.NavigationItem

interface FavoriteShowsSource {
    fun getFavoriteShows(): List<NavigationItem>
}