package com.tvmaze.challenge.domain.entities

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val title: String, val icon: ImageVector, val screen_route: String) {
    companion object {
        private const val SHOWS_LIST_ROUTE = "shows_screen"
        private const val SHOWS_LIST_TITLE = "shows_navigation"
        const val SHOW_DETAIL_ROUTE = "show_detail_screen/"
        private const val SHOW_DETAIL_DEEP_LINK_ROUTE = "show_detail_screen/{show}"
        private const val SHOW_DETAIL_TITLE = "show_detail_navigation"
        private const val PEOPLE_ROUTE = "people_screen"
        private const val PEOPLE_TITLE = "people_navigation"
        private const val FAVORITES_ROUTE = "favorites_screen"
        private const val FAVORITES_TITLE = "favorites_navigation"
    }

    object ShowsListScreen: NavigationItem(SHOWS_LIST_TITLE, Icons.Filled.PlayArrow, SHOWS_LIST_ROUTE)
    object ShowDetailScreen: NavigationItem(SHOW_DETAIL_TITLE, Icons.Filled.Info, SHOW_DETAIL_DEEP_LINK_ROUTE)
    object PeopleScreen: NavigationItem(PEOPLE_TITLE, Icons.Filled.Person, PEOPLE_ROUTE)
    object FavoritesScreen: NavigationItem(FAVORITES_TITLE, Icons.Filled.Favorite, FAVORITES_ROUTE)
}