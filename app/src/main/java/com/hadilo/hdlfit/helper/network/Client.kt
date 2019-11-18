package com.hadilo.hdlfit.helper.network

import com.hadilo.hdlfit.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Client {

    val BASE_URL = "https://api.backendless.com/${BuildConfig.BACKENDLESS_APPLICATION_ID}/${BuildConfig.BACKENDLESS_API_KEY}/"

    fun getData(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}