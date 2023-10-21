package com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDate

@Immutable
data class KalendarEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null
)

/**
 * Represents a collection of calendar events.
 *
 * @param events The list of calendar events.
 */
@Immutable
data class KalendarEvents(
    val events: List<KalendarEvent> = emptyList()
)
