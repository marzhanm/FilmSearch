package com.example.filmsearch.data
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmsearch.data.Film
import com.example.filmsearch.data.FilmDao
import com.example.filmsearch.data.AppDatabase


@Database(entities = [Film::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao // Соединяем базу данных с DAO
}