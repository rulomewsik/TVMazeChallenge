package com.tvmaze.challenge.local.sources

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import com.tvmaze.challenge.R
import com.tvmaze.challenge.domain.entities.BottomNavigationItem
import com.tvmaze.challenge.domain.sources.local.ShowsListSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ShowsListSourceImpl @Inject constructor(@ApplicationContext private val context: Context) :
    ShowsListSource {
    override fun getBottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                title = context.getString(R.string.shows_navigation),
                icon = Icons.Filled.PlayArrow
                ),
            BottomNavigationItem(
                title = context.getString(R.string.people_navigation),
                icon = Icons.Filled.Person
                ),
            BottomNavigationItem(
                title = context.getString(R.string.favorites_navigation),
                icon = Icons.Filled.Favorite
                )
        )
    }
}