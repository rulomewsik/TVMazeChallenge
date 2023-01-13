package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResolutionsModel (
    @SerializedName("original" ) var original : ResolutionModel? = ResolutionModel(),
    @SerializedName("medium"   ) var medium   : ResolutionModel? = ResolutionModel()
): Serializable