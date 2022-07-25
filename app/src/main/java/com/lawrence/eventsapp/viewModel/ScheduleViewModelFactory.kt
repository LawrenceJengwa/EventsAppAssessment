package com.lawrence.eventsapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lawrence.eventsapp.network.NetworkRepo

class ScheduleViewModelFactory constructor(private val repo: NetworkRepo): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
                ScheduleViewModel(this.repo) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
}