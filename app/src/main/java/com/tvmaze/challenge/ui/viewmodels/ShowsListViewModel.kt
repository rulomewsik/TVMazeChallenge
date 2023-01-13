package com.tvmaze.challenge.ui.viewmodels

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tvmaze.challenge.domain.entities.BottomNavigationItem
import com.tvmaze.challenge.domain.usecases.FavoriteShowsUseCase
import com.tvmaze.challenge.domain.usecases.TVMazeUseCase
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsListViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val tvMazeUseCase: TVMazeUseCase
): AndroidViewModel(context as Application) {

    //region Properties

    private var _showsList = MutableStateFlow<List<TVShowModel>>(emptyList())
    var showsList: StateFlow<List<TVShowModel>> = _showsList

    //endregion

    init {
        viewModelScope.launch {
            getAllTVShows()
        }
    }

    suspend fun getAllTVShows() {
        viewModelScope.launch {
            tvMazeUseCase.getAllTVShows()?.let { _showsList.tryEmit(it) }
        }
    }

    fun getTVShowsByPage(page: Int): Flow<PagingData<TVShowModel>> = tvMazeUseCase.getShows().cachedIn(viewModelScope)
}