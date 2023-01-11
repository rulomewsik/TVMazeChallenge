package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ScheduleModel(
    @SerializedName("time" ) var time : String?      = null,
    @SerializedName("days" ) var days : List<String> = listOf()
): Serializable