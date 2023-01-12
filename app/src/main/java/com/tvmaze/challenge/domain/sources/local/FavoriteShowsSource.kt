package com.tvmaze.challenge.domain.sources.local

import com.tvmaze.challenge.domain.entities.BottomNavigationItem

interface FavoriteShowsSource {
    fun getFavoriteShows(): List<BottomNavigationItem>
}