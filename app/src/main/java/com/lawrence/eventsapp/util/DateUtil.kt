package com.lawrence.eventsapp.util

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import kotlin.properties.Delegates

class DateUtil {

    fun sortDate(date: String) {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        sdf.parse(date)

    }

companion object {

    private fun dateTimeToMills(date: String): Long? {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        var timeInMills = 1L
        try {
            val date = simpleDateFormat.parse(date)
            timeInMills = date?.time!!
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
}
}