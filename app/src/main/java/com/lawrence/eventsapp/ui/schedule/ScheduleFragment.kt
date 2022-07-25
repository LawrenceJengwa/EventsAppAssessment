package com.lawrence.eventsapp.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawrence.eventsapp.databinding.FragmentEventsBinding
import com.lawrence.eventsapp.network.NetworkAdapter
import com.lawrence.eventsapp.network.NetworkRepo
import com.lawrence.eventsapp.network.Schedule
import com.lawrence.eventsapp.viewModel.ScheduleViewModel
import com.lawrence.eventsapp.viewModel.ViewModelFactory

class ScheduleFragment : Fragment() {
    private var _binding: FragmentEventsBinding? = null
    private lateinit var viewModel: ScheduleViewModel
    private val networkAdapter = NetworkAdapter.getInstance()
    private val repo = NetworkRepo(networkAdapter)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, ViewModelFactory(repo)).get(ScheduleViewModel::class.java)
        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        viewModel.scheduleList.observe(viewLifecycleOwner){
            initAdapter(it)
        }
    }

    private fun initAdapter(scheduleList: List<Schedule>) {
        val scheduleAdapter = ScheduleAdapter(scheduleList)
        binding.eventsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.eventsRecyclerView.adapter = scheduleAdapter
        scheduleAdapter.notifyDataSetChanged()
    }

    private fun setUpViewModel() {
        viewModel.getSchedule()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}