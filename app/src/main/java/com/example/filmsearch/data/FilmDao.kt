package com.example.filmsearch.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.filmsearch.data.Film
import com.example.filmsearch.data.FilmDao
import com.example.filmsearch.data.AppDatabase
import androidx.room.OnConflictStrategy


@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: Film) // Добавить фильм с заменой при конфликте

    @Query("SELECT * FROM films WHERE collection = :collection")
    suspend fun getFilmsByCollection(collection: String): List<Film> // Получить фильмы по категории

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<Film> // Получить все фильмы

    @Query("DELETE FROM films WHERE collection = :collection")
    suspend fun deleteFilmsByCollection(collection: String) // Удалить фильмы по категории

    @Delete
    suspend fun deleteFilm(film: Film) // Удалить конкретный фильм
}

