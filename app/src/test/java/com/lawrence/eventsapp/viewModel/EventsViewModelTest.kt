package com.lawrence.eventsapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lawrence.eventsapp.network.Events
import com.lawrence.eventsapp.network.NetworkRepo
import com.lawrence.eventsapp.network.NetworkService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class EventsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var eventsViewModel: EventsViewModel
    @Mock
    lateinit var repo: NetworkRepo
    @Mock
    lateinit var networkService: NetworkService
    @get:Rule
    val instantTaskExecutionRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        //repo = mock(NetworkRepo::class.java)
        repo = NetworkRepo(networkService)
        eventsViewModel = EventsViewModel(repo)
    }

    @Test
    fun `getAllEvents test` () {
        runBlocking {
            whenever(repo.getAllEvents()).thenReturn(
                Response.success(listOf<Events>(Events("1", "Liverpool v Porto",
                "UEFA Champions League", "2022-07-24T01:17:34.757Z", "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310176837169_image-header_pDach_1554579780000.jpeg?alt=media&token=1777d26b-d051-4b5f-87a8-7633d3d6dd20",
                "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media")
                )))
            val result = eventsViewModel.eventList.value?.get(0)
            assertEquals(listOf<TestData>(), result)
        }
    }

    @Test
    fun `empty events list test`() {
        runBlocking {
            Mockito.`when`(repo.getAllEvents())
                .thenReturn(Response.success(listOf<Events>()))
            eventsViewModel.getAllEvents()
            val result = eventsViewModel.eventList.value
            assertEquals(listOf<TestData>(), result)
        }
    }

    object TestData {
        const val id = "1"
        const val title = "Liverpool v Porto"
        const val subtitle = "UEFA Champions League"
        const val date = "2022-07-24T01:17:34.757Z"
        const val imageUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310176837169_image-header_pDach_1554579780000.jpeg?alt=media&token=1777d26b-d051-4b5f-87a8-7633d3d6dd20"
        const val videoUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
    }
}