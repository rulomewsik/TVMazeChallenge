package com.tvmaze.challenge.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShowEpisodeModel (
    @SerializedName("id"       ) var id       : Int?         = null,
    @SerializedName("url"      ) var url      : String?      = null,
    @SerializedName("name"     ) var name     : String?      = null,
    @SerializedName("season"   ) var season   : Int?         = null,
    @SerializedName("number"   ) var number   : Int?         = null,
    @SerializedName("type"     ) var type     : String?      = null,
    @SerializedName("airdate"  ) var airdate  : String?      = null,
    @SerializedName("airtime"  ) var airtime  : String?      = null,
    @SerializedName("airstamp" ) var airstamp : String?      = null,
    @SerializedName("runtime"  ) var runtime  : Int?         = null,
    @SerializedName("rating"   ) var rating   : RatingModel? = RatingModel(),
    @SerializedName("image"    ) var image    : ImageModel?  = ImageModel(),
    @SerializedName("summary"  ) var summary  : String?      = null,
    @SerializedName("_links"   ) var links    : LinksModel?  = LinksModel()
): Serializable