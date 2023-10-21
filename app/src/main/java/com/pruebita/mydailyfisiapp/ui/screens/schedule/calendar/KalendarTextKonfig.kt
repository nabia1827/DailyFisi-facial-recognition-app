package com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Immutable
data class KalendarTextKonfig(
    val kalendarTextColor: Color,
    val kalendarTextSize: TextUnit
) {
    companion object {
        /**
         * Creates a default configuration with the specified text color.
         * @param color The color of the header text.
         * @return The default configuration.
         */
        internal fun default(color: Color) = KalendarTextKonfig(
            kalendarTextColor = color,
            kalendarTextSize = 24.sp
        )

        /**
         * Creates a preview default configuration for previewing purposes.
         * @return The preview default configuration.
         */
        @SuppressWarnings("MagicNumber")
        internal fun previewDefault() = KalendarTextKonfig(
            kalendarTextSize = 24.sp,
            kalendarTextColor = Color(0xFFD2827A)
        )
    }
}