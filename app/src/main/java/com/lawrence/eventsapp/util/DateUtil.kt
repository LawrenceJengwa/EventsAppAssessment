package com.lawrence.eventsapp.util

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.hours

class DateUtil {

    companion object {
        const val VIDEO_URL_KEY = "VIDEO_URL"
        private fun dateTimeToMills(date: String): Long? {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:SS.ss'Z'")

            var timeInMills = 1L
            try {
                val date = simpleDateFormat.parse(date)
                timeInMills = date?.time!!
                date.time.hours
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return timeInMills
        }

        fun isToday(date: String): Boolean {
            if (dateTimeToMills(date)?.let {
                    DateUtils.isToday(it)
                } == true) {
                return true
            }
            return false
        }

        fun extractTimeFromDate(date: String): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:SS.ss'Z'")

            try {
                val date = simpleDateFormat.parse(date)
                var calender = Calendar.getInstance()
                if (date != null) {
                    calender.time = date
                }
                return ", " + calender.get(Calendar.HOUR_OF_DAY) + ":" + calender.get(Calendar.MINUTE)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return ""
        }
    }
}