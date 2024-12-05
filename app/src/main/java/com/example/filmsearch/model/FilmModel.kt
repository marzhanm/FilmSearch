package com.example.filmsearch.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class FilmModel(
    @SerializedName("films")
    val film2s: List<Film2>,

    @SerializedName("keyword")
    val keyword: String,

    @SerializedName("pagesCount")
    val pagesCount: Int,

    @SerializedName("searchFilmsCountResult")
    val searchFilmsCountResult: Int
)
