package com.lawrence.eventsapp.ui.events


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lawrence.eventsapp.R
import com.lawrence.eventsapp.databinding.EventsItemBinding
import com.lawrence.eventsapp.network.Events
import com.lawrence.eventsapp.util.DateUtil
import com.squareup.picasso.Picasso

class EventsAdapter(private var eventList: List<Events>) : RecyclerView.Adapter<EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventsItemBinding.inflate(inflater, parent, false)
        return EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = eventList[position]
        holder.display(event, position)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}

class EventsViewHolder(private val binding: EventsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun display(event: Events, position: Int) {
        binding.apply {
            eventTitle.text = event.title
            eventSubTitle.text = event.subtitle
            if (event.date?.let { DateUtil.isToday(it) } == true) {
                eventDate.text = binding.eventDate.context.getString(R.string.today)
            } else {
                eventDate.text = binding.eventDate.context.getString(R.string.yesterday)
            }
            Picasso.get()
                .load(event.imageUrl)
                .resize(20, 20)
                .into(binding.eventImage)

        }
        binding.eventsContainer.setOnClickListener {
            prepareVideo(event, position)
        }
    }

    private fun prepareVideo(event: Events, position: Int) {
    }

}