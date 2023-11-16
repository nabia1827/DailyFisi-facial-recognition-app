package com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar

import androidx.compose.runtime.MutableState
import com.pruebita.mydailyfisiapp.data.model.domain.KalendarSelectedDayRange
import kotlinx.datetime.LocalDate

internal fun onDayClicked(
    date: LocalDate,
    events: List<KalendarEvent>,
    daySelectionMode: DaySelectionMode,
    selectedRange: MutableState<KalendarSelectedDayRange?>,
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> }
) {
    when (daySelectionMode) {
        DaySelectionMode.Single -> {
            onDayClick(date, events)
        }

        DaySelectionMode.Range -> {
            val range = selectedRange.value
            selectedRange.value = when {
                range?.isEmpty() != false -> KalendarSelectedDayRange(start = date, end = date)
                range.isSingleDate() -> KalendarSelectedDayRange(start = range.start, end = date)
                else -> KalendarSelectedDayRange(start = date, end = date)
            }

            selectedRange.value?.let { rangeDates ->
                val selectedEvents = events
                    .filter { it.date in (rangeDates.start..rangeDates.end) }
                    .toList()

                onRangeSelected(rangeDates, selectedEvents)
            }
        }
    }
}