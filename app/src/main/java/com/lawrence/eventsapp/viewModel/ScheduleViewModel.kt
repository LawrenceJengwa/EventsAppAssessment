package com.lawrence.eventsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lawrence.eventsapp.network.NetworkRepo
import com.lawrence.eventsapp.network.Schedule
import kotlinx.coroutines.*

class ScheduleViewModel(private val repo: NetworkRepo) : ViewModel() {
    private val errorMessage = MutableLiveData<String>()
    val scheduleList = MutableLiveData<List<Schedule>>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val isLoading = MutableLiveData<Boolean>()
    private var job: Job? = null

    fun getSchedule(){
        job  = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.getSchedule()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    scheduleList.postValue(response.body())
                    isLoading.value =false
                } else{
                    onError("Error : ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}