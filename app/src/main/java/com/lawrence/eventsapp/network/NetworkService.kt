package com.lawrence.eventsapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface NetworkService {
    @Headers("Content-Type: application/json")
    @GET("getEvents")
    suspend fun getAllEvents() : Response<List<Events>>

    @Headers("Content-Type: application/json")
    @GET("getSchedule")
    suspend fun getSchedule(): Response<List<Schedule>>

}