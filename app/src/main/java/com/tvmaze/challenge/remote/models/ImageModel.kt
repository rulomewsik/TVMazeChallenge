package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageModel (
    @SerializedName("medium"   ) var medium   : String? = null,
    @SerializedName("original" ) var original : String? = null
): Serializable