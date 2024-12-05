package com.example.filmsearch.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class Film(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val collection: String
)
