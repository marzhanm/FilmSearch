package com.example.filmsearch.network

import com.example.filmsearch.model.FilmModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/api/v2.1/films/search-by-keyword")
    suspend fun getKeywordFilm(
//        @Query("part") part: String,
        @Query("id") id: String
    ) : Call<FilmModel>
}