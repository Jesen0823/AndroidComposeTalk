package com.jesen.jetpackdatastore.demo1

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val PREFERENCE_NAME = "my_preference"

class DataStoreRepository(context: Context) {

    private object PreferenceKeys {
        val name = preferencesKey<String>("my_name")
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCE_NAME
    )

   suspend fun saveToDataStore(content: String) {
       dataStore.edit { preference ->
           preference[PreferenceKeys.name] = content
       }
   }

   val readFromDataStore: Flow<String> = dataStore.data
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