package com.lawrence.eventsapp.network

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import retrofit2.Response

@RunWith(JUnit4::class)
class NetworkRepoTest {

    @Mock
    lateinit var repo: NetworkRepo
    @Mock
    lateinit var networkService: NetworkService

    @Test
    suspend fun `getAllEvents should return list of events`() {
        Mockito.`when`(networkService.getAllEvents()).thenReturn(Response.success(listOf()))
        val response = repo.getAllEvents()
        assertEquals(listOf<Events>(), response.body())
    }

    @Test
    suspend fun `getSchedule should return a schedule list` () {
        Mockito.`when`(networkService.getSchedule()).thenReturn(Response.success(listOf()))
        val response = repo.getSchedule()
        assertEquals(listOf<Events>(), response.body())
    }
}