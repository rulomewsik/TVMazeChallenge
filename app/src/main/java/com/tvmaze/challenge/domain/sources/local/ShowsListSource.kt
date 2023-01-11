package com.tvmaze.challenge.domain.sources.local

import com.tvmaze.challenge.domain.entities.BottomNavigationItem

interface ShowsListSource {
    fun getBottomNavigationItems(): List<BottomNavigationItem>
}