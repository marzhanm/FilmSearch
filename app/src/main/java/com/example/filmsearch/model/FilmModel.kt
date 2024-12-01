package com.example.filmsearch.model

import android.content.ClipData.Item
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FilmModel(
    @SerializedName("films")
    val films: List<Film>,

    @SerializedName("keyword")
    val keyword: String,

    @SerializedName("pagesCount")
    val pagesCount: Int,

    @SerializedName("searchFilmsCountResult")
    val searchFilmsCountResult: Int
)
