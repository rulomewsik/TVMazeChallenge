package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShowImagesModel(
    @SerializedName("id"          ) var id          : Int?              = null,
    @SerializedName("type"        ) var type        : String?           = null,
    @SerializedName("main"        ) var main        : Boolean?          = null,
    @SerializedName("resolutions" ) var resolutions : ResolutionsModel? = ResolutionsModel()
): Serializable