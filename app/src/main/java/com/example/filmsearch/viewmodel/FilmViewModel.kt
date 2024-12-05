package com.example.filmsearch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.filmsearch.data.Film
import com.example.filmsearch.data.FilmDao
import com.example.filmsearch.data.AppDatabase
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class FilmViewModel(application: Application) : AndroidViewModel(application) {

    // DAO для взаимодействия с базой данных
    private val dao: FilmDao = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "films_db"
    ).build().filmDao()

    // LiveData для подсчёта фильмов в категориях
    private val _filmCounts = MutableLiveData<Map<String, Int>>()
    val filmCounts: LiveData<Map<String, Int>> get() = _filmCounts

    // LiveData для списка фильмов по категории
    private val _films = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>> get() = _films

    // Блок init для инициализации данных
    init {
        addSampleFilms() // Добавляем тестовые фильмы при запуске приложения
    }

    // Добавление тестовых фильмов в базу данных
    fun addSampleFilms() {
        viewModelScope.launch {

            dao.insertFilm(Film(title = "Фильм 1", collection = "Любимые"))
            println("Added to 'Любимые': Фильм 1")

            dao.insertFilm(Film(title = "Фильм 2", collection = "Любимые"))
            println("Added to 'Любимые': Фильм 2")

            dao.insertFilm(Film(title = "Фильм 3", collection = "Избранные"))
            println("Added to 'Избранные': Фильм 3")

            dao.insertFilm(Film(title = "Фильм 4", collection = "Избранные"))
            println("Added to 'Избранные': Фильм 4")

            dao.insertFilm(Film(title = "Фильм 5", collection = "Русское кино"))
            println("Added to 'Русское кино': Фильм 5")
        }
    }


    // Загрузка количества фильмов в категориях
    fun loadFilmCounts() {
        viewModelScope.launch {
            val categories = listOf("Любимые", "Избранные", "Русское кино")
            val counts = categories.associateWith { category ->
                dao.getFilmsByCollection(category).size
            }
            _filmCounts.postValue(counts)
        }
    }

    // Загрузка фильмов для конкретной категории
    fun loadFilms(category: String) {
        viewModelScope.launch {
            _films.postValue(dao.getFilmsByCollection(category))
        }
    }
}

