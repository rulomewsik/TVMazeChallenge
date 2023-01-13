package com.tvmaze.challenge.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tvmaze.challenge.domain.usecases.TVMazeUseCase
import com.tvmaze.challenge.remote.models.ShowSearchModel
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
) : AndroidViewModel(context as Application) {

    //region Properties

    private val _searchBarState: MutableState<SearchBarState> =
        mutableStateOf(SearchBarState.CLOSED)
    val searchBarState: State<SearchBarState> = _searchBarState

    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    private var _allShowsList = MutableStateFlow<List<TVShowModel>>(emptyList())
    var allShowsList: StateFlow<List<TVShowModel>> = _allShowsList

    private var _searchShowsList = MutableStateFlow<List<ShowSearchModel>>(emptyList())
    var searchShowsList: StateFlow<List<ShowSearchModel>> = _searchShowsList

    //endregion

    fun updateSearchBarState(newState: SearchBarState) {
        _searchBarState.value = newState

        if (newState == SearchBarState.CLOSED) {
            _searchShowsList.tryEmit(emptyList())
        }
    }

    fun updateSearchTextState(newText: String) {
        _searchTextState.value = newText
    }

    suspend fun getAllTVShows() {
        viewModelScope.launch {
            tvMazeUseCase.getAllTVShows()?.let { _allShowsList.tryEmit(it) }
        }
    }

    fun getTVShowsPagerFlow(): Flow<PagingData<TVShowModel>> =
        tvMazeUseCase.getShowsPager().cachedIn(viewModelScope)

    suspend fun getShowsByName(name: String) {
        viewModelScope.launch {
            tvMazeUseCase.getShowsByName(name)?.let { _searchShowsList.tryEmit(it) }
        }
    }
}