package com.lawrence.eventsapp.viewModel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lawrence.eventsapp.network.Events
import com.lawrence.eventsapp.network.NetworkRepo
import com.lawrence.eventsapp.network.NetworkService
import com.lawrence.eventsapp.network.Schedule
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response


class ScheduleViewModelTest {

    @ExperimentalCoroutinesApi
    class EventsViewModelTest {

        private val testDispatcher = TestCoroutineDispatcher()

        @Mock
        lateinit var scheduleViewModel: ScheduleViewModel
        @Mock
        lateinit var repo: NetworkRepo
        @Mock
        lateinit var networkService: NetworkService
        //@get:Rule
        //val instantTaskExecutionRule = InstantTaskExecutionRule()

        @Before
        fun setUp() {
            MockitoAnnotations.initMocks(this)
            Dispatchers.setMain(testDispatcher)
            repo = mock(NetworkRepo::class.java)
            scheduleViewModel = ScheduleViewModel(repo)
        }


        @Test
        fun `getSchedule test` () {
            runBlocking {
                `when`(repo.getSchedule()).thenReturn(
                    Response.success(listOf<Schedule>(
                        Schedule("1", "Liverpool v Porto",
                        "UEFA Champions League", "2022-07-24T01:17:34.757Z", "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310176837169_image-header_pDach_1554579780000.jpeg?alt=media&token=1777d26b-d051-4b5f-87a8-7633d3d6dd20")
                    )))
                val result = scheduleViewModel.scheduleList.value
                TestCase.assertEquals(listOf<TestData>(), result)
            }
        }

        @Test
        fun `empty schedule list test`() {
            runBlocking {
                Mockito.`when`(repo.getAllEvents())
                    .thenReturn(Response.success(listOf<Events>()))
                scheduleViewModel.getSchedule()
                val result = scheduleViewModel.scheduleList.value
                TestCase.assertEquals(listOf<TestData>(), result)
            }
        }

        object TestData {
            const val id = "1"
            const val title = "Liverpool v Porto"
            const val subtitle = "UEFA Champions League"
            const val date = "2022-07-24T01:17:34.757Z"
            const val imageUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310176837169_image-header_pDach_1554579780000.jpeg?alt=media&token=1777d26b-d051-4b5f-87a8-7633d3d6dd20"
        }
    }
}