package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RatingModel(
    @SerializedName("average" ) var average : String? = null
): Serializable