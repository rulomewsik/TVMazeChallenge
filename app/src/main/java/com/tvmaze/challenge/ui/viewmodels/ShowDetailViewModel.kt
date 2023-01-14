package com.tvmaze.challenge.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tvmaze.challenge.domain.usecases.TVMazeUseCase
import com.tvmaze.challenge.remote.models.ShowEpisodeModel
import com.tvmaze.challenge.remote.models.ShowImagesModel
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
class ShowDetailViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val tvMazeUseCase: TVMazeUseCase
) : AndroidViewModel(context as Application) {

    //region Properties

    var show by mutableStateOf(TVShowModel())

    private var _showImages = MutableStateFlow<List<ShowImagesModel>>(emptyList())
    var showImages: StateFlow<List<ShowImagesModel>> = _showImages

    private var _showMainImage = MutableStateFlow(ShowImagesModel())
    var showMainImage: StateFlow<ShowImagesModel> = _showMainImage

    private var _showBannerImage = MutableStateFlow(ShowImagesModel())
    var showBannerImage: StateFlow<ShowImagesModel> = _showBannerImage

    private var _showEpisodes = MutableStateFlow<List<ShowEpisodeModel>>(emptyList())
    var showEpisodes: StateFlow<List<ShowEpisodeModel>> = _showEpisodes

    //endregion

    suspend fun getShowImages(showId: Int) {
        viewModelScope.launch {
            tvMazeUseCase.getShowImages(showId)?.let { imagesList ->
                _showImages.tryEmit(imagesList)
                _showMainImage.tryEmit(imagesList.first { it.main == true })

                _showBannerImage.tryEmit(imagesList.first { it.type == "banner" || it.type == "background"  } )
            }
        }
    }

    suspend fun getShowEpisodes(showId: Int) {
        viewModelScope.launch {
            tvMazeUseCase.getEpisodesByShowId(showId)?.let { _showEpisodes.tryEmit(it) }
        }
    }
}