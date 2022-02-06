package com.mwdev.composedemoapp.models.calendar

data class CalendarDay(
    val dayKey: Long,
    val dayNumber: Int,
    val isFromOffset: Boolean,
    val isSelected: Boolean
)