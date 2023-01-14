package com.tvmaze.challenge.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tvmaze.challenge.domain.entities.ExpandableSeasonCardItem
import com.tvmaze.challenge.domain.usecases.TVMazeUseCase
import com.tvmaze.challenge.remote.models.ShowEpisodeModel
import com.tvmaze.challenge.remote.models.ShowImagesModel
import com.tvmaze.challenge.remote.models.TVShowModel
import com.tvmaze.challenge.ui.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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

    private val _seasonCards = MutableStateFlow(listOf<ExpandableSeasonCardItem>())
    val seasonCards: StateFlow<List<ExpandableSeasonCardItem>> = _seasonCards

    private val _expandedSeasonCards = MutableStateFlow(listOf<Int>())
    val expandedSeasonCards: StateFlow<List<Int>> = _expandedSeasonCards

    private var _showEpisodes = MutableStateFlow<List<ShowEpisodeModel>>(emptyList())
    var showEpisodes: StateFlow<List<ShowEpisodeModel>> = _showEpisodes

    //endregion

    suspend fun getShowImages(showId: Int) {
        viewModelScope.launch {
            tvMazeUseCase.getShowImages(showId)?.let { imagesList ->
                _showImages.tryEmit(imagesList)
                _showMainImage.tryEmit(imagesList.first { it.main == true })

                _showBannerImage.tryEmit(imagesList.first { it.type == "banner" || it.type == "background" })
            }
        }
    }

    suspend fun getShowEpisodes(showId: Int) {
        viewModelScope.launch {
            tvMazeUseCase.getEpisodesByShowId(showId)?.let { episodesList ->
                _showEpisodes.tryEmit(episodesList)
            }

            tvMazeUseCase.getShowSeasons(showId)?.let { seasons ->
                val seasonCardsList = mutableListOf<ExpandableSeasonCardItem>()
                seasons.forEach { season ->
                    seasonCardsList.add(
                        ExpandableSeasonCardItem(
                            id = season.number!!,
                            title = "Season " + season.number,
                            episodes = _showEpisodes.value.filter { it.season == season.number }
                        )
                    )
                }
                _seasonCards.tryEmit(seasonCardsList)
            }
        }
    }

    fun onSeasonCardArrowClicked(cardId: Int) {
        _expandedSeasonCards.value =
            _expandedSeasonCards.value.toMutableList().also { list ->
                if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
            }
    }
}