package com.pruebita.mydailyfisiapp.ui.screens.schedule

import Kalendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarType
import com.pruebita.mydailyfisiapp.ui.screens.schedule.components.CardCurso
import com.pruebita.mydailyfisiapp.ui.screens.schedule.components.CardHora
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import okhttp3.internal.wait
import java.time.format.TextStyle
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen(){
    var today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    var currentDay by remember{
        mutableStateOf<LocalDate>(today)
    }
    var minimize = remember{
        mutableStateOf<Boolean>(false)
    }
    var label = remember{
        mutableStateOf<String>("Mes")
    }
    var kalendarType:KalendarType = KalendarType.Firey


    LazyColumn (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ){
        item{
            Box(modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
            ){
                headerCalendar(currentDay, minimize, label)
            }

            if(minimize.value){
                kalendarType = KalendarType.Oceanic
                label.value = "Semana"

            }
            else{
                kalendarType = KalendarType.Firey
                label.value = "Mes"
            }

            Column (
                modifier= Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .heightIn(min = 0.dp, max = 500.dp),
            ){
                Kalendar(
                    currentDay = currentDay,
                    kalendarType = kalendarType,
                    modifier= Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    onDayClick = { selectedDay, events ->
                        currentDay = selectedDay
                    },
                )
            }

            Box(
                modifier = Modifier
            )
            {
                gridCards()
            }

        }
    }
}

@Composable
fun gridCards() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)

    ){
        Text(
            text = "06 Sep",
            fontSize = 28.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 2.dp)
        )
        Row(){
            Column(
                verticalArrangement = Arrangement.SpaceAround,
            ) {
                Text(
                    text = "Hora",
                    color = Color(0xFFBCC1CD),
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                CardHora()
                CardHora()
                CardHora()
                CardHora()
                CardHora()
            }
            Spacer(Modifier.padding(15.dp))
            Column (
                verticalArrangement = Arrangement.SpaceAround,
            ){
                Text(
                    text = "Curso",
                    color = Color(0xFFBCC1CD),
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                CardCurso(true)
                CardCurso()
                CardCurso()
                CardCurso()
                CardCurso()
            }
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun headerCalendar(date: LocalDate, minimize: MutableState<Boolean>, label: MutableState<String>) {
    val locale = Locale("es", "ES")
    var nombreDia = date.dayOfWeek.getDisplayName(TextStyle.FULL, locale)
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
    var nombreMes = date.month.getDisplayName(TextStyle.FULL, locale)
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

    ) {
        Box(
            modifier = Modifier
                .weight(0.2f), // Proporción 1:1:1,
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = date.dayOfMonth.toString(),
                fontSize = 28.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold
            )
        }

        Column(
            modifier = Modifier
                .weight(0.5f) // Proporción 1:1:1
        ) {
            Text(
                text = nombreDia,
                fontSize = 18.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "$nombreMes ${date.year}",
                fontSize = 16.sp,
                fontFamily = poppins,
                color = Color(0xFFBCC1CD),
                fontWeight = FontWeight.Medium
            )
        }
        Box(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize(),

            contentAlignment = Alignment.Center
        ){
            FilledTonalButton(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .fillMaxWidth(),
                onClick = {
                    minimize.value = !minimize.value
                },

                ) {
                Text(
                    label.value,
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}