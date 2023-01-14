package com.tvmaze.challenge.domain.entities

import com.tvmaze.challenge.remote.models.ShowEpisodeModel

data class ExpandableSeasonCardItem(val id: Int, val title: String, val episodes: List<ShowEpisodeModel>)