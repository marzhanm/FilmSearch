package com.example.filmsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HistoryViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {
    // Локальное состояние для хранения истории
    private val _historyList = MutableStateFlow<List<String>>(emptyList())
    val historyList: StateFlow<List<String>> = _historyList

    init {
        loadHistory() // Загружаем историю при создании ViewModel
    }

    // Загрузка истории из DataStore
    private fun loadHistory() {
        viewModelScope.launch {
            dataStoreManager.historyFlow.collect { history ->
                _historyList.value = history.toList()
            }
        }
    }

    // Очистка истории
    fun clearHistory() {
        viewModelScope.launch {
            dataStoreManager.clearHistory() // Очистка данных в DataStore
            _historyList.value = emptyList() // Обновление состояния
        }
    }
}
