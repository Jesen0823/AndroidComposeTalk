package com.jesen.paging3demo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        const val BASEURL = "https://way.jd.com/"

        fun getRetroInstance(): Retrofit {

            val logInterceptor = HttpLoggingInterceptor()
            //暂时使用常量true来替代BuildConfig.DEBUG
            if (true) {
                //显示日志
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }

            val okhttpClient = OkHttpClient.Builder().addInterceptor(logInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)//设置超时时间
                .retryOnConnectionFailure(true).build()

            return Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}