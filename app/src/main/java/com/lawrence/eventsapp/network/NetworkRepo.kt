package com.lawrence.eventsapp.network

class NetworkRepo constructor(private val networkService: NetworkService) {
    suspend fun getAllEvents() = networkService.getAllEvents()
    suspend fun getSchedule() = networkService.getSchedule()
}