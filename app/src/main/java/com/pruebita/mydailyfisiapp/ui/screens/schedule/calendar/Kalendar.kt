import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.DaySelectionMode
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarColors
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarDayKonfig
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarEvent
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarEvents
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarFirey
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarOceanic
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarSelectedDayRange
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarTextKonfig
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarType
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.RangeSelectionError
import kotlinx.datetime.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Kalendar(
    currentDay: LocalDate?,
    kalendarType: KalendarType,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    Kalendar(
        currentDay = currentDay,
        kalendarType = kalendarType,
        modifier = modifier,
        daySelectionMode = daySelectionMode,
        showLabel = showLabel,
        kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
        kalendarColors = kalendarColors,
        kalendarDayKonfig = kalendarDayKonfig,
        onDayClick = onDayClick,
        dayContent = dayContent,
        headerContent = headerContent,
        events = KalendarEvents(),
        onRangeSelected = onRangeSelected,
        onErrorRangeSelected = onErrorRangeSelected
    )
}

/**
 * Composable function that represents a calendar component.
 *
 * @param currentDay The current selected day in the calendar.
 * @param kalendarType The type of calendar to be displayed.
 * @param modifier The modifier for styling or positioning the calendar.
 * @param showLabel Determines whether to show the labels for days of the week.
 * @param events The events to be displayed in the calendar.
 * @param kalendarHeaderTextKonfig The configuration for the header text in the calendar.
 * @param kalendarColors The colors used for styling the calendar.
 * @param kalendarDayKonfig The configuration for each day cell in the calendar.
 * @param daySelectionMode The mode for selecting days in the calendar.
 * @param dayContent The content to be displayed inside each day cell of the calendar.
 * @param headerContent The content to be displayed in the header of the calendar.
 * @param onDayClick Callback function triggered when a day cell is clicked.
 * @param onRangeSelected Callback function triggered when a range of days is selected.
 * @param onErrorRangeSelected Callback function triggered when an error occurs during range selection.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Kalendar(
    currentDay: LocalDate?,
    kalendarType: KalendarType,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    events: KalendarEvents = KalendarEvents(),
    kalendarHeaderTextKonfig: KalendarTextKonfig? = null,
    kalendarColors: KalendarColors = KalendarColors.default(),
    kalendarDayKonfig: KalendarDayKonfig = KalendarDayKonfig.default(),
    daySelectionMode: DaySelectionMode = DaySelectionMode.Single,
    dayContent: (@Composable (LocalDate) -> Unit)? = null,
    headerContent: (@Composable (Month, Int) -> Unit)? = null,
    onDayClick: (LocalDate, List<KalendarEvent>) -> Unit = { _, _ -> },
    onRangeSelected: (KalendarSelectedDayRange, List<KalendarEvent>) -> Unit = { _, _ -> },
    onErrorRangeSelected: (RangeSelectionError) -> Unit = {}
) {
    when (kalendarType) {
        KalendarType.Oceanic -> {
            KalendarOceanic(
                currentDay = currentDay,
                modifier = modifier,
                showLabel = showLabel,
                kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
                kalendarColors = kalendarColors,
                events = events,
                kalendarDayKonfig = kalendarDayKonfig,
                onDayClick = onDayClick,
                dayContent = dayContent,
                headerContent = headerContent,
                daySelectionMode = daySelectionMode,
                onRangeSelected = onRangeSelected,
                onErrorRangeSelected = onErrorRangeSelected
            )
        }

        KalendarType.Firey -> {
            KalendarFirey(
                currentDay = currentDay,
                modifier = modifier,
                showLabel = showLabel,
                kalendarHeaderTextKonfig = kalendarHeaderTextKonfig,
                kalendarColors = kalendarColors,
                events = events,
                kalendarDayKonfig = kalendarDayKonfig,
                onDayClick = onDayClick,
                dayContent = dayContent,
                headerContent = headerContent,
                daySelectionMode = daySelectionMode,
                onRangeSelected = onRangeSelected,
                onErrorRangeSelected = onErrorRangeSelected
            )
        }
    }
}
