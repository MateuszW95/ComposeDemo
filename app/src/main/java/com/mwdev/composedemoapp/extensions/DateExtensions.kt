package com.mwdev.composedemoapp.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

fun Calendar.monthsBetween(otherCalendar: Calendar): Int {
    val month = get(Calendar.MONTH)
    val year = get(Calendar.YEAR)
    val otherMonth = otherCalendar.get(Calendar.MONTH)
    val otherYear = otherCalendar.get(Calendar.YEAR)

    return abs((12 * year + month) - (12 * otherYear + otherMonth))
}

fun Calendar.resetToStartOfMonth(): Calendar {
    set(Calendar.DAY_OF_MONTH, 1)
    return this
}

fun String.toLocalDate(
    pattern: String,
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).apply {
            this.timeZone = timeZone
        }.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Date.displayDateWith(
    pattern: String,
    locale: Locale = Locale.getDefault(),
    timezone: TimeZone = TimeZone.getDefault()
): String {
    return SimpleDateFormat(pattern, locale).apply { timeZone = timezone }.format(this)
}

fun Date.toCalendar(): Calendar {
    return Calendar.getInstance().also {
        it.time = this
    }
}

fun Calendar.getMonthKey(): Int {
    return (get(Calendar.YEAR) * 100) + get(Calendar.MONTH)
}

fun Calendar.getDayKey(): Long {
    return ((get(Calendar.YEAR) * 10000) + (get(Calendar.MONTH) * 100) + (get(Calendar.DAY_OF_MONTH))).toLong()
}