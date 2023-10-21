package com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar

sealed interface DaySelectionMode {
    /**
     * Represents the single day selection mode.
     */
    object Single : DaySelectionMode

    /**
     * Represents the range selection mode.
     */
    object Range : DaySelectionMode
}

/**
 * Sealed interface representing the range selection errors in a Kalendar.
 */
sealed interface RangeSelectionError {
    /**
     * Represents the error when the end date is before the start date in a range selection.
     */
    object EndIsBeforeStart : RangeSelectionError
}