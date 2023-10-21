package com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun KalendarOceanic(
    modifier: Modifier = Modifier,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    currentDay: LocalDate? = null,
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    events: KalendarEvents = KalendarEvents(),
    labelFormat: (DayOfWeek) -> String = {
        var nameDay = it.getDisplayName(
            TextStyle.SHORT,
            Locale("es", "ES")
        )
        var shortDay = nameDay.substring(0, 1).toUpperCase()
        shortDay
    },
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: @Composable ((LocalDate) -> Unit)? = null,
    headerContent: @Composable ((Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    val today = currentDay ?: Clock.System.todayIn(TimeZone.currentSystemDefault())
    val weekValue = remember { mutableStateOf(today.getNext7Dates()) }
    val yearAndMonth = getCurrentMonthAndYear(weekValue.value)
    var selectedDate by remember { mutableStateOf(today) }
    val currentMonthIndex = yearAndMonth.first.value.minus(1)
    val selectedRange = remember { mutableStateOf<KalendarSelectedDayRange?>(null) }

    Column(
        modifier = modifier
            .background(
                color = kalendarColors.color[currentMonthIndex].backgroundColor
            )
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        /*if (headerContent != null) {
            headerContent(yearAndMonth.first, yearAndMonth.second)
        } else {
            KalendarHeader(
                month = yearAndMonth.first,
                year = yearAndMonth.second,
                kalendarTextKonfig = kalendarHeaderTextKonfig ?: KalendarTextKonfig(
                    kalendarTextColor = kalendarColors.color[currentMonthIndex].headerTextColor,
                    kalendarTextSize = 24.sp
                ),
                onPreviousClick = {
                    val firstDayOfDisplayedWeek = weekValue.value.first()
                    weekValue.value = firstDayOfDisplayedWeek.getPrevious7Dates()
                },
                onNextClick = {
                    val lastDayOfDisplayedWeek = weekValue.value.last().plus(1, DateTimeUnit.DAY)
                    weekValue.value = lastDayOfDisplayedWeek.getNext7Dates()
                },
            )
        }*/
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            content = {
                itemsIndexed(weekValue.value) { _, item ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (showLabel) {
                            Text(
                                modifier = Modifier,
                                color = Color(0xFFBCC1CD),
                                fontSize = kalendarDayKonfig.textSize,
                                text = labelFormat(item.dayOfWeek),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))

                        if (dayContent != null) {
                            dayContent(item)
                        } else {
                            KalendarDay(
                                date = item,
                                kalendarColors = kalendarColors.color[currentMonthIndex],
                                onDayClick = { clickedDate, event ->
                                    onDayClicked(
                                        clickedDate,
                                        event,
                                        daySelectionMode,
                                        selectedRange,
                                        onRangeSelected = { range, events ->
                                            if (range.end < range.start) {
                                                onErrorRangeSelected(RangeSelectionError.EndIsBeforeStart)
                                            } else {
                                                onRangeSelected(range, events)
                                            }
                                        },
                                        onDayClick = { newDate, clickedDateEvent ->
                                            selectedDate = newDate
                                            onDayClick(newDate, clickedDateEvent)
                                        }
                                    )
                                },
                                selectedDate = selectedDate,
                                kalendarEvents = events,
                                kalendarDayKonfig = kalendarDayKonfig,
                                selectedRange = selectedRange.value,
                            )
                        }
                    }
                }
            }
        )
    }
    Divider(
        color = Color(0x99BCC1CD), // Color de la línea
        thickness = 1.dp,    // Grosor de la línea
        modifier = Modifier.fillMaxWidth() // Ancho completo
    )
}

/**
 * Calculates the current month and year based on a list of dates representing a week.
 * @param weekValue The list of dates representing a week.
 * @return A pair containing the current month and year.
 */
private fun getCurrentMonthAndYear(weekValue: List<LocalDate>): Pair<Month, Int> {
    val month = weekValue.first().month
    val year = weekValue.first().year
    return Pair(month, year)
}

@RequiresApi(Build.VERSION_CODES.O)
@MultiplePreviews
@Composable
fun KalendarOceanicPreview() {
    KalendarOceanic(
        currentDay = Clock.System.todayIn(
            TimeZone.currentSystemDefault()
        ),
        kalendarHeaderTextKonfig = KalendarTextKonfig.previewDefault(),
        daySelectionMode = DaySelectionMode.Single
    )
}
