package com.tvmaze.challenge.ui.viewmodels

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tvmaze.challenge.domain.entities.BottomNavigationItem
import com.tvmaze.challenge.domain.sources.local.ShowsListSource
import com.tvmaze.challenge.domain.usecases.ShowsListUseCase
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
class ShowsListViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val tvMazeUseCase: TVMazeUseCase,
    private val showsListUseCase: ShowsListUseCase
): AndroidViewModel(context as Application) {

    //region Properties

    private var _bottomNavigationMenuItems = MutableStateFlow<List<BottomNavigationItem>>(emptyList())
    var bottomNavigationMenuItems: StateFlow<List<BottomNavigationItem>> = _bottomNavigationMenuItems

    //endregion

    init {
        getShowsListBottomNavigation()
    }

    private fun getShowsListBottomNavigation() {
        viewModelScope.launch {
            _bottomNavigationMenuItems.tryEmit(showsListUseCase.getBottomNavigationItems())
        }
    }

    suspend fun getAllTVShows(): List<TVShowModel> {
        return tvMazeUseCase.getAllTVShows() ?: emptyList()
    }
}