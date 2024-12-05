package com.example.filmsearch.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import retrofit2.http.Url

@Keep
data class FilmDAO2(
    @SerializedName("nameRu")
    val nameRu: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("year")
    val year: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("genre")
    val genre: String,

    @SerializedName("ratingVoteCount")
    val ratingVoteCount: String,

    @SerializedName("posterUrlPreview")
    val url: Url
)
