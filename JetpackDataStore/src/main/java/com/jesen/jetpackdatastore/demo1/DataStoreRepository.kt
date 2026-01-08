package com.jesen.jetpackdatastore.demo1

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val PREFERENCE_NAME = "my_preference"

class DataStoreRepository(private val context: Context) {

    private object PreferenceKeys {
        val name = stringPreferencesKey("my_name")
    }

    private val Context.dataStore by preferencesDataStore(
        name = PREFERENCE_NAME
    )

    suspend fun saveToDataStore(content: String) {
        context.dataStore.edit { preference ->
            preference[PreferenceKeys.name] = content
        }
    }

    val readFromDataStore: Flow<String> = context.dataStore.data
        .catch { e ->
            if(e is IOException){
                Log.d("DataStore",e.message.toString())
                emit(emptyPreferences())
            }else{
                throw  e
            }
        }
        .map { preference ->
            val myName = preference[PreferenceKeys.name] ?: "none"
            myName
        }

}