package com.lawrence.eventsapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface NetworkAdapter {
    companion object {
        var retrofitService: NetworkService? = null
        var loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

        var client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        var gson = GsonBuilder()
            .setLenient()
            .create()

        fun getInstance() : NetworkService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://us-central1-dazn-sandbox.cloudfunctions.net/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
                retrofitService = retrofit.create(NetworkService::class.java)
            }
            return retrofitService!!
        }

    }
}