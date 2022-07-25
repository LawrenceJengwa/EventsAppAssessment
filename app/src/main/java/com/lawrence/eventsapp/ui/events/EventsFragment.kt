package com.lawrence.eventsapp.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lawrence.eventsapp.R
import com.lawrence.eventsapp.databinding.FragmentEventsBinding
import com.lawrence.eventsapp.network.Events
import com.lawrence.eventsapp.network.NetworkAdapter
import com.lawrence.eventsapp.network.NetworkRepo
import com.lawrence.eventsapp.viewModel.EventsViewModel
import com.lawrence.eventsapp.viewModel.ViewModelFactory


class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private lateinit var viewModel: EventsViewModel
    private val networkAdapter = NetworkAdapter.getInstance()
    private val repo = NetworkRepo(networkAdapter)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, ViewModelFactory(repo)).get(EventsViewModel::class.java)
        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        viewModel.eventList.observe(viewLifecycleOwner){
            initAdapter(it)
        }
    }

    private fun initAdapter(eventList: List<Events>) {
        val eventsAdapter = EventsAdapter(eventList)
        binding.eventsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.eventsRecyclerView.adapter = eventsAdapter
        eventsAdapter.notifyDataSetChanged()
    }

    private fun setUpViewModel() {
        viewModel.getAllEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}