package com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pruebita.mydailyfisiapp.data.model.domain.KalendarTextKonfig
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun KalendarHeader(
    month: Month,
    year: Int,
    kalendarTextKonfig: KalendarTextKonfig,
    modifier: Modifier = Modifier,
    onPreviousClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    arrowShown: Boolean = true
) {
    var isNext by remember { mutableStateOf(true) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val titleText = remember(month, year) { getTitleText(month, year) }
        if (arrowShown) {
            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                KalendarIconButton(
                    modifier = Modifier.wrapContentSize(),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous Month",
                    onClick = {
                        isNext = false
                        onPreviousClick()
                    }
                )
                AnimatedContent(
                    targetState = titleText,
                    transitionSpec = {
                        addAnimation(isNext = isNext).using(
                            SizeTransform(clip = false)
                        )
                    }, label = ""
                ) { targetText ->
                    Text(
                        modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically),
                        color = kalendarTextKonfig.kalendarTextColor,
                        fontSize = kalendarTextKonfig.kalendarTextSize,
                        text = targetText,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start
                    )
                }
                KalendarIconButton(
                    modifier = Modifier.wrapContentSize(),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next Month",
                    onClick = {
                        isNext = true
                        onNextClick()
                    }
                )
            }
        }


    }
}

/**
 * Adds the animation to the content based on the given duration and direction.
 *
 * @param duration The duration of the animation in milliseconds.
 * @param isNext Determines the direction of the animation.
 * @return The content transformation with the specified animation.
 */
@OptIn(ExperimentalAnimationApi::class)
private fun addAnimation(duration: Int = 200, isNext: Boolean): ContentTransform {
    return slideInVertically(
        animationSpec = tween(durationMillis = duration)
    ) { height -> if (isNext) height else -height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(
        animationSpec = tween(durationMillis = duration)
    ) { height -> if (isNext) -height else height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}

/**
 * Returns the formatted title text for the Kalendar header.
 *
 * @param month The current month.
 * @param year The current year.
 * @return The formatted title text.
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun getTitleText(month: Month, year: Int): String {
    val locale = Locale("es", "ES") // Crea un Locale para el español de España
    val monthDisplayName = month.getDisplayName(TextStyle.FULL, locale)
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
    return "$monthDisplayName"
}

@RequiresApi(Build.VERSION_CODES.O)
@MultiplePreviews
@Composable
fun KalendarHeaderPreview() {
    KalendarHeader(
        month = Month.APRIL,
        year = 2023,
        kalendarTextKonfig = KalendarTextKonfig.previewDefault()
    )
}
