package com.jesen.jetpackdatastore.demo2

import android.content.Context
import android.widget.Toast
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {

    private val dataSource = context.createDataStore(name = "user_pref")

    companion object {
        val USER_NAME_KEY = preferencesKey<String>("USER_NAME")
        val USER_AGE_KEY = preferencesKey<Int>("USER_AGE")
        val USER_GENDER_KEY = preferencesKey<Boolean>("USER_GENDER")
    }

    suspend fun saveUser(age: Int, name: String, isMale: Boolean) {
        dataSource.edit {
            it[USER_NAME_KEY] = name
            it[USER_AGE_KEY] = age
            it[USER_GENDER_KEY] = isMale
        }
    }

    val userNameFlow: Flow<String> = dataSource.data.map {
        it[USER_NAME_KEY] ?: ""
    }

    val userAgeFlow: Flow<Int> = dataSource.data.map {
       val age = it[USER_AGE_KEY] ?: 0
        if(age < 18){
            Toast.makeText(context,"The user under 18 ",Toast.LENGTH_SHORT).show()
        }
        age
    }

    val userGenderFlow: Flow<Boolean> = dataSource.data.map {
        it[USER_GENDER_KEY] ?: false
    }



}