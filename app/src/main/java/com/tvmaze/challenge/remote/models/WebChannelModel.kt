package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WebChannelModel (
    @SerializedName("id"           ) var id           : Int?          = null,
    @SerializedName("name"         ) var name         : String?       = null,
    @SerializedName("country"      ) var country      : CountryModel? = CountryModel(),
    @SerializedName("officialSite" ) var officialSite : String?       = null
): Serializable