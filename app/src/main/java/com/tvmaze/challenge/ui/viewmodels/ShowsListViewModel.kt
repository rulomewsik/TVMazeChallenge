package com.tvmaze.challenge.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tvmaze.challenge.domain.entities.BottomNavigationItem
import com.tvmaze.challenge.domain.usecases.FavoriteShowsUseCase
import com.tvmaze.challenge.domain.usecases.TVMazeUseCase
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.Application
import com.tvmaze.challenge.utils.SearchBarState
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

    private val _searchBarState: MutableState<SearchBarState> = mutableStateOf(SearchBarState.CLOSED)
    val searchBarState: State<SearchBarState> = _searchBarState

    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    private var _showsList = MutableStateFlow<List<TVShowModel>>(emptyList())
    var showsList: StateFlow<List<TVShowModel>> = _showsList

    //endregion

    init {
        viewModelScope.launch {
            getAllTVShows()
        }
    }

    fun updateSearchBarState(newState: SearchBarState) {
        _searchBarState.value = newState
    }

    fun updateSearchTextState(newText: String) {
        _searchTextState.value = newText
    }

    suspend fun getAllTVShows() {
        viewModelScope.launch {
            tvMazeUseCase.getAllTVShows()?.let { _showsList.tryEmit(it) }
        }
    }

    fun getTVShowsByPage(page: Int): Flow<PagingData<TVShowModel>> = tvMazeUseCase.getShows().cachedIn(viewModelScope)
}