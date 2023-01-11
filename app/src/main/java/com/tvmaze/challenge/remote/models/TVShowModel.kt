package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TVShowModel (
    @SerializedName("id"             ) var id             : Int?              = null,
    @SerializedName("url"            ) var url            : String?           = null,
    @SerializedName("name"           ) var name           : String?           = null,
    @SerializedName("type"           ) var type           : String?           = null,
    @SerializedName("language"       ) var language       : String?           = null,
    @SerializedName("genres"         ) var genres         : List<String>      = listOf(),
    @SerializedName("status"         ) var status         : String?           = null,
    @SerializedName("runtime"        ) var runtime        : Int?              = null,
    @SerializedName("averageRuntime" ) var averageRuntime : Int?              = null,
    @SerializedName("premiered"      ) var premiered      : String?           = null,
    @SerializedName("ended"          ) var ended          : String?           = null,
    @SerializedName("officialSite"   ) var officialSite   : String?           = null,
    @SerializedName("schedule"       ) var schedule       : ScheduleModel?    = ScheduleModel(),
    @SerializedName("rating"         ) var rating         : RatingModel?      = RatingModel(),
    @SerializedName("weight"         ) var weight         : Int?              = null,
    @SerializedName("network"        ) var network        : NetworkModel?     = NetworkModel(),
    @SerializedName("webChannel"     ) var webChannel     : WebChannelModel?  = WebChannelModel(),
    @SerializedName("dvdCountry"     ) var dvdCountry     : CountryModel?     = CountryModel(),
    @SerializedName("externals"      ) var externals      : ExternalsModel?   = ExternalsModel(),
    @SerializedName("image"          ) var image          : ImageModel?       = ImageModel(),
    @SerializedName("summary"        ) var summary        : String?           = null,
    @SerializedName("updated"        ) var updated        : Int?              = null,
    @SerializedName("_links"         ) var links          : LinksModel?       = LinksModel()
): Serializable