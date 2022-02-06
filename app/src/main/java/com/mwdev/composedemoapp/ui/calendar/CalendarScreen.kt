package com.mwdev.composedemoapp.ui.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.math.MathUtils
import com.mwdev.composedemoapp.R
import com.mwdev.composedemoapp.extensions.*
import com.mwdev.composedemoapp.helper.CalendarHelper
import com.mwdev.composedemoapp.models.calendar.CalendarDay
import com.mwdev.composedemoapp.models.calendar.CalendarMonthConfig
import java.util.*
import kotlin.math.absoluteValue


@Composable
fun CalendarScreen(navigation: NavController) {
    val startCalendar = "1-2022".toLocalDate("M-yyyy")?.toCalendar() ?: Calendar.getInstance()
    val endCalendar = "4-2023".toLocalDate("M-yyyy")?.toCalendar() ?: Calendar.getInstance()

    val selectedDayKey = remember {
        mutableStateOf(Calendar.getInstance().getDayKey())
    }

    Calendar(
        startCalendar,
        endCalendar,
        minDayHeight = 40.dp,
        selectedDayKey = selectedDayKey.value,
        onDayChanged = { dayKey -> selectedDayKey.value = dayKey }
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Calendar(
    startCalendar: Calendar,
    endCalendar: Calendar,
    minDayHeight: Dp,
    selectedDayKey: Long,
    onDayChanged: (Long) -> Unit
) {
    val monthCount = remember(startCalendar, endCalendar, selectedDayKey) {
        mutableStateOf(startCalendar.monthsBetween(endCalendar) + 1)
    }

    val monthConfigs = remember {
        mutableStateOf(listOf<CalendarMonthConfig>())
    }

    LaunchedEffect(key1 = selectedDayKey) {
        val startCalendarCopy =
            startCalendar.time.displayDateWith("01-MM-yyyy").toLocalDate("dd-MM-yyyy")
                ?.toCalendar() ?: Calendar.getInstance()
        val endCalendarCopy =
            endCalendar.time.displayDateWith("02-MM-yyyy").toLocalDate("dd-MM-yyyy")
                ?.toCalendar() ?: Calendar.getInstance()

        val array = arrayListOf<CalendarMonthConfig>()
        while (startCalendarCopy.time.before(endCalendarCopy.time)) {
            array.add(
                CalendarMonthConfig.create(
                    startCalendarCopy.clone() as Calendar,
                    selectedDayKey
                )
            )
            startCalendarCopy.add(Calendar.MONTH, 1)
        }
        monthConfigs.value = array
    }

    HorizontalPager(count = monthCount.value) { page ->
        MonthView(
            monthConfig = monthConfigs.value.getOrElse(page) { return@HorizontalPager },
            minDayHeight = minDayHeight,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 3.dp, end = 3.dp)
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = ScaleFactor(0.85f, 0.85f),
                        stop = ScaleFactor(1f, 1f),
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale.scaleX
                        scaleY = scale.scaleX
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = MathUtils.lerp(
                        0.1f,
                        1f,
                        1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
            onDayChanged
        )
    }
}

@Composable
fun MonthView(
    monthConfig: CalendarMonthConfig,
    minDayHeight: Dp,
    modifier: Modifier = Modifier,
    onDayChanged: (Long) -> Unit
) {
    Column(modifier = modifier) {
        //month name
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            text = monthConfig.label,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        //days names
        Row(modifier = Modifier.fillMaxWidth()) {
            (0..6).onEach { index ->
                DayName(dayIndex = index, modifier = Modifier.weight(1 / 7f))
            }
        }
        //days
        val weeks = monthConfig.days.size.div(7)
        val maxDaysContainerHeight = minDayHeight.times(6) // 6 is max of week number
        val avgDayHeight = maxDaysContainerHeight.div(weeks)
        for (row in 0 until weeks) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (column in 0 until 7) {
                    Day(
                        calendarDay = monthConfig.days[(row * 7) + column],
                        modifier = Modifier
                            .weight(1 / 7f)
                            .height(avgDayHeight),
                        onDayChanged
                    )
                }
            }
        }
    }
}

@Composable
fun DayName(
    dayIndex: Int, modifier: Modifier = Modifier
) {
    Text(
        text = CalendarHelper.getDayName(dayIndex),
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Day(calendarDay: CalendarDay, modifier: Modifier = Modifier, onDayChanged: (Long) -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (calendarDay.isFromOffset) return@clickable else
                        onDayChanged(calendarDay.dayKey)
                },
            text = calendarDay.dayNumber.toString(),
            color = if (calendarDay.isFromOffset) Color.Gray else if (calendarDay.isSelected) Color.Red else Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = if (calendarDay.isFromOffset) FontWeight.Thin else FontWeight.Bold,
        )
        Image(
            painter = rememberImagePainter(R.drawable.ic_grill),
            contentDescription = "LOGO",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
}

