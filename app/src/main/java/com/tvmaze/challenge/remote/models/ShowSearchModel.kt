package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShowSearchModel (
    @SerializedName("score" ) var score  : Double?      = null,
    @SerializedName("show"  ) var show   : TVShowModel? = TVShowModel()
): Serializable