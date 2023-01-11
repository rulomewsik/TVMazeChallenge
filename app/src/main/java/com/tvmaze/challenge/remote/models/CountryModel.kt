package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryModel (
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("code"     ) var code     : String? = null,
    @SerializedName("timezone" ) var timezone : String? = null
): Serializable