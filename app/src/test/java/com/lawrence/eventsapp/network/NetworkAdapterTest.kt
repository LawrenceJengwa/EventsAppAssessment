package com.lawrence.eventsapp.network

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkAdapterTest {

    lateinit var networkService: NetworkService
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        gson = Gson()
        networkService = Retrofit.Builder()
            .baseUrl("https://us-central1-dazn-sandbox.cloudfunctions.net/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(NetworkService::class.java)
    }

    @Test
    fun `getAllEvents test`() {
        runBlocking {
        //TODO: implement test case

        }
    }
}