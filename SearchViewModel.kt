package com.example.myapplication

import KinopoiskApiClient
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _searchResults = MutableStateFlow<List<Film>>(emptyList())
    val searchResults: StateFlow<List<Film>> = _searchResults

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    fun searchFilms(keyword: String, page: Int = 1) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = ""
            try {
                val response = KinopoiskApiClient(token = "7d8e8e10-821d-44b5-bc3f-e3982846c68b").searchFilms(keyword)
                _searchResults.value = response
            } catch (e: Exception) {
                _error.value = "Failed to load data: ${e.message}"
                _searchResults.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}
