package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShowSeasonModel (
    @SerializedName("id"           ) var id           : Int?             = null,
    @SerializedName("url"          ) var url          : String?          = null,
    @SerializedName("number"       ) var number       : Int?             = null,
    @SerializedName("name"         ) var name         : String?          = null,
    @SerializedName("episodeOrder" ) var episodeOrder : Int?             = null,
    @SerializedName("premiereDate" ) var premiereDate : String?          = null,
    @SerializedName("endDate"      ) var endDate      : String?          = null,
    @SerializedName("network"      ) var network      : NetworkModel?    = NetworkModel(),
    @SerializedName("webChannel"   ) var webChannel   : WebChannelModel? = WebChannelModel(),
    @SerializedName("image"        ) var image        : ImageModel?      = ImageModel(),
    @SerializedName("summary"      ) var summary      : String?          = null,
    @SerializedName("_links"       ) var links        : LinksModel?      = LinksModel()
): Serializable