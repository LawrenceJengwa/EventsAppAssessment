package com.lawrence.eventsapp.network

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class NetworkRepoTest {

    @Mock
    lateinit var repo: NetworkRepo
    @Mock
    lateinit var networkService: NetworkService

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repo = NetworkRepo(networkService)
    }

    @Test
    fun `getAllEvents should return list of events`() {
        runBlocking {
            Mockito.`when`(networkService.getAllEvents()).thenReturn(Response.success(listOf()))
            val response = repo.getAllEvents()
            assertEquals(listOf<Events>(), response.body())
        }
    }

    @Test
    fun `getSchedule should return a schedule list` () {
        runBlocking {
            Mockito.`when`(networkService.getSchedule()).thenReturn(Response.success(listOf()))
            val response = repo.getSchedule()
            assertEquals(listOf<Events>(), response.body())
        }
    }
}