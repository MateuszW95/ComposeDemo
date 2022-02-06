package com.mwdev.composedemoapp.models.calendar

import com.mwdev.composedemoapp.extensions.displayDateWith
import com.mwdev.composedemoapp.extensions.getDayKey
import com.mwdev.composedemoapp.extensions.getMonthKey
import com.mwdev.composedemoapp.extensions.resetToStartOfMonth
import java.util.*

data class CalendarMonthConfig(val label: String, val days: List<CalendarDay>) {

    companion object {
        fun create(calendar: Calendar, selectedDayKey: Long): CalendarMonthConfig {
            val label = calendar.time.displayDateWith("MMMM-YYYY")
            val offset = calendar.get(Calendar.DAY_OF_WEEK) - 1 //Sunday = 1, Monday = 2 ...
            val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

            val currentMonthKey = calendar.getMonthKey()

            val fifthWeekVisible = daysInMonth + offset > 4 * 7
            val sixthWeekVisible = daysInMonth + offset > 5 * 7
            val realDaysInMonth = if (sixthWeekVisible) 42 else if (fifthWeekVisible) 35 else 28
            val days = arrayListOf<CalendarDay>()

            calendar.resetToStartOfMonth()
            if (offset > 0) {
                calendar.add(Calendar.DAY_OF_MONTH, -offset)
            }

            for (i in 0..realDaysInMonth) {
                val monthKey = calendar.getMonthKey()
                val dayKey = calendar.getDayKey()
                val day = CalendarDay(
                    dayKey = dayKey,
                    dayNumber = calendar.get(Calendar.DAY_OF_MONTH),
                    isFromOffset = currentMonthKey != monthKey,
                    isSelected = dayKey == selectedDayKey
                )
                days.add(day)
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }

            return CalendarMonthConfig(label = label, days = days)
        }
    }
}
