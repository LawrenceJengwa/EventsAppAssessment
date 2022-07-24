package com.lawrence.eventsapp.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScheduleViewModel : ViewModel() {
//TODO: implement schedule
    private val _text = MutableLiveData<String>().apply {
        value = "This is Schedule Fragment"
    }
    val text: LiveData<String> = _text
}