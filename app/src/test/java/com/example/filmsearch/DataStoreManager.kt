package com.example.filmsearch

import android.content.Context
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.edit


// Расширение для DataStore
val Context.dataStore by preferencesDataStore(name = "history_store")

class DataStoreManager(private val context: Context) {
    // Ключ для сохранения истории
    private val HISTORY_KEY = stringSetPreferencesKey("history_key")

    // Получение данных истории из DataStore
    val historyFlow: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[HISTORY_KEY] ?: emptySet()
    }

    // Метод для очистки данных истории
    suspend fun clearHistory() {
        context.dataStore.edit { preferences ->
            preferences[HISTORY_KEY] = emptySet()
        }
    }
}
