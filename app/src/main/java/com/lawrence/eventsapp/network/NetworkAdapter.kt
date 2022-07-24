package com.lawrence.eventsapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface NetworkAdapter {
    companion object {
        var retrofitService: NetworkService? = null
        fun getInstance() : NetworkService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://us-central1-dazn-sandbox.cloudfunctions.net")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(NetworkService::class.java)
            }
            return retrofitService!!
        }

    }
}