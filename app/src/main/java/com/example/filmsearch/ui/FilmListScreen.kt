package com.example.filmsearch.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.filmsearch.viewmodel.FilmViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun FilmListScreen(category: String, viewModel: FilmViewModel = hiltViewModel()) {
    // Наблюдаем за списком фильмов
    val films by viewModel.films.observeAsState(initial = emptyList())

    // Загружаем фильмы при входе на экран
    LaunchedEffect(category) {
        viewModel.loadFilms(category)
    }

    // Отображаем фильмы
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Категория: $category",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(films) { film ->
                Text(
                    text = film.title,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
