package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ExternalsModel (
    @SerializedName("tvrage"  ) var tvrage  : Int?    = null,
    @SerializedName("thetvdb" ) var thetvdb : Int?    = null,
    @SerializedName("imdb"    ) var imdb    : String? = null
): Serializable