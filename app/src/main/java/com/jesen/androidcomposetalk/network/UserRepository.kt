package com.jesen.androidcomposetalk.network

object UserRepository {
    suspend fun startLogin(username: String, password: String) =
        RetrofitClient.createApi(LoginServiceApi::class.java)
            .requestLogin(username, password)
}