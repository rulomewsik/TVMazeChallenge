package com.tvmaze.challenge.ui.viewmodels

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tvmaze.challenge.domain.entities.BottomNavigationItem
import com.tvmaze.challenge.domain.usecases.FavoriteShowsUseCase
import com.tvmaze.challenge.domain.usecases.TVMazeUseCase
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteShowsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val tvMazeUseCase: TVMazeUseCase,
    private val favoriteShowsUseCase: FavoriteShowsUseCase
): AndroidViewModel(context as Application) {

    //region Properties

    private var _bottomNavigationMenuItems = MutableStateFlow<List<BottomNavigationItem>>(emptyList())
    var bottomNavigationMenuItems: StateFlow<List<BottomNavigationItem>> = _bottomNavigationMenuItems

    //endregion


    private suspend fun getFavoriteShows() {
        viewModelScope.launch {

        }
    }
}