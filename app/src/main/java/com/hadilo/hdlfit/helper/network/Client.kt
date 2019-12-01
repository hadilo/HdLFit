package com.hadilo.hdlfit.helper.network

import com.hadilo.hdlfit.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Hadilo Muhammad on 2019-10-06.
 */

class Client {

    val BASE_URL = "https://api.backendless.com/${BuildConfig.BACKENDLESS_APPLICATION_ID}/${BuildConfig.BACKENDLESS_API_KEY}/"

    fun getData(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptorLoggingEndPoint())
            .connectTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit
    }

    private fun interceptorLoggingEndPoint(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}