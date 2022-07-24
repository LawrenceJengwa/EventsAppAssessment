package com.lawrence.eventsapp.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lawrence.eventsapp.network.Events
import com.lawrence.eventsapp.network.NetworkRepo
import kotlinx.coroutines.*

class EventsViewModel constructor(private val repo: NetworkRepo): ViewModel() {

    private val errorMessage = MutableLiveData<String>()
    private val eventsList = MutableLiveData<List<Events>>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val isLoading = MutableLiveData<Boolean>()
    private var job: Job? = null

   fun getAllEvents(){
        job  = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repo.getAllEvents()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    eventsList.postValue(response.body())
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