package com.example.myapplication

data class FilmResponse(
    val films: List<Film>
)

data class Film(
    val nameRu: String?,
    val nameEn: String?,
    val year: Int?,
    val rating: Double?,
    val posterUrl: String?
)
data class User(
    val id: Int,
    val name: String,
    val username: String
)
