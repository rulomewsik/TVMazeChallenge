package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SelfModel (
    @SerializedName("href" ) var href : String? = null
): Serializable