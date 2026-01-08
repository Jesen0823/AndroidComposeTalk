package com.jesen.jetpackdatastore.demo1

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferences(
    context: Context
) {
    private val applicationContext = context.applicationContext
    
    private val Context.dataStore by preferencesDataStore(name = "my_data_store")

    val authToken: Flow<String?> get() = applicationContext.dataStore.data.map { it[KEY_AUTH] }

    suspend fun saveAuthToken(authToken: String) {
        applicationContext.dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    companion object {
        private val KEY_AUTH = stringPreferencesKey("auth")
    }
}