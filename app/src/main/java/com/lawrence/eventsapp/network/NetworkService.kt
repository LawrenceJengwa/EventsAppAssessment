package com.lawrence.eventsapp.network

import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("events.json")
    suspend fun getAllEvents() : Response<List<Events>>

    @GET("schedule.json")
    suspend fun getSchedule(): Response<List<Schedule>>

}