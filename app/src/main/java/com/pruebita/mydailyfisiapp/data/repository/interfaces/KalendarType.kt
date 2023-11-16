package com.pruebita.mydailyfisiapp.data.repository.interfaces
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