package com.mwdev.composedemoapp.helper

object CalendarHelper {

    fun getDayName(index: Int): String {
        return when (index) {
            0 -> "Su"
            1 -> "Mo"
            2 -> "Tu"
            3 -> "We"
            4 -> "Th"
            5 -> "Fr"
            6 -> "Sa"
            else -> ""
        }
    }

}