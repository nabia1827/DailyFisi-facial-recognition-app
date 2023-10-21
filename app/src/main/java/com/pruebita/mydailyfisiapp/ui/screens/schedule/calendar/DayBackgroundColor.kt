package com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDate

private const val FULL_ALPHA = 1f
private const val TOWNED_DOWN_ALPHA = 0.4F
fun Modifier.dayBackgroundColor(
    selected: Boolean,
    color: Color,
    date: LocalDate,
    selectedRange: KalendarSelectedDayRange?
): Modifier {
    val inRange = date == selectedRange?.start || date == selectedRange?.end

    val backgroundColor:Brush = when {
        selected -> Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
        selectedRange != null && date in selectedRange.start..selectedRange.end -> {
            Brush.horizontalGradient(
                colors = listOf(Color(0xFFEBECF0), Color(0xFFEBECF0))
            )
        }
        else -> Brush.horizontalGradient(
            colors = listOf(Color.Transparent, Color.Transparent)
        )
    }

    return this.then(
        background(brush = backgroundColor)
    )
}