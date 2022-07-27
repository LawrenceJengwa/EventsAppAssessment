package com.lawrence.eventsapp.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lawrence.eventsapp.R
import com.lawrence.eventsapp.databinding.EventsItemBinding
import com.lawrence.eventsapp.databinding.ScheduleItemBinding
import com.lawrence.eventsapp.network.Schedule
import com.lawrence.eventsapp.util.DateUtil
import com.squareup.picasso.Picasso

class ScheduleAdapter(private var scheduleList: List<Schedule>) :
    RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ScheduleItemBinding.inflate(inflater, parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val event = scheduleList[position]
        holder.display(event)
    }

    override fun getItemCount(): Int {
        return scheduleList.size
    }
}

class ScheduleViewHolder(private val binding: ScheduleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun display(schedule: Schedule) {
        binding.apply {
            scheduleTitle.text = schedule.title
            //scheduleDate.text = schedule.date
            if (DateUtil.isToday(schedule.date)) {
                scheduleDate.text = scheduleDate.context.getString(R.string.today)
            } else {
                scheduleDate.text = scheduleDate.context.getString(R.string.yesterday)
            }
            Picasso.get()
                .load(schedule.imageUrl)
                .resize(20, 20)
                .into(binding.scheduleImage)
        }
    }

}