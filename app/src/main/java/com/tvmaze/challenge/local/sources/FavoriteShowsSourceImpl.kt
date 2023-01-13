package com.tvmaze.challenge.local.sources

import android.content.Context
import com.tvmaze.challenge.domain.entities.NavigationItem
import com.tvmaze.challenge.domain.sources.local.FavoriteShowsSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FavoriteShowsSourceImpl @Inject constructor(@ApplicationContext private val context: Context) :
    FavoriteShowsSource {
    override fun getFavoriteShows(): List<NavigationItem> {
        return emptyList()
    }
}