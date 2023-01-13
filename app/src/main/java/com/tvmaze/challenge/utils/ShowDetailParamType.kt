package com.tvmaze.challenge.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.tvmaze.challenge.remote.models.TVShowModel

class ShowDetailParamType : NavType<TVShowModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): TVShowModel? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): TVShowModel {
        return Gson().fromJson(value, TVShowModel::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: TVShowModel) {
        bundle.putParcelable(key, value)
    }
}