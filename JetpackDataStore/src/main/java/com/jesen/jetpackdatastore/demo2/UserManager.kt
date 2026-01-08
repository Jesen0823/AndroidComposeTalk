package com.jesen.jetpackdatastore.demo2

import android.content.Context
import android.widget.Toast
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "user_pref")

    companion object {
        val USER_NAME_KEY = stringPreferencesKey("USER_NAME")
        val USER_AGE_KEY = intPreferencesKey("USER_AGE")
        val USER_GENDER_KEY = booleanPreferencesKey("USER_GENDER")
    }

    suspend fun saveUser(age: Int, name: String, isMale: Boolean) {
        context.dataStore.edit {
            it[USER_NAME_KEY] = name
            it[USER_AGE_KEY] = age
            it[USER_GENDER_KEY] = isMale
        }
    }

    val userNameFlow: Flow<String> = context.dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }

    val userAgeFlow: Flow<Int> = context.dataStore.data.map {
       val age = it[USER_AGE_KEY] ?: 0
        if(age < 18){
            Toast.makeText(context,"The user under 18 ",Toast.LENGTH_SHORT).show()
        }
        age
    }

    val userGenderFlow: Flow<Boolean> = context.dataStore.data.map {
        it[USER_GENDER_KEY] ?: false
    }



}