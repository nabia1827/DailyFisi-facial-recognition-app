package com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar
sealed interface KalendarType {
    /**
     * Firey calendar type.
     */
    object Firey : KalendarType

    /**
     * Oceanic calendar type.
     */
    object Oceanic : KalendarType
}