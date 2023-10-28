package com.pruebita.mydailyfisiapp.ui.screens.schedule

import Kalendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.navigation.InternalScreens
import com.pruebita.mydailyfisiapp.ui.screens.schedule.calendar.KalendarType
import com.pruebita.mydailyfisiapp.ui.screens.schedule.components.CardCurso
import com.pruebita.mydailyfisiapp.ui.screens.schedule.components.CardHora
import com.pruebita.mydailyfisiapp.ui.screens.schedule.components.CardRecordatorio
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewScheduleScreen(){
    val navController = rememberNavController()
    ScheduleScreen(navController)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen(navController: NavHostController){
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
    var showMyDialog = remember{
        mutableStateOf<Boolean>(false)
    }
    MyDialog(showMyDialog)


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
                gridCards(navController,currentDay)
            }

            Column(
                modifier = Modifier
            )
            {
                Recordatorios(navController,showMyDialog)
            }



        }
    }
}

@Composable
fun Recordatorios(navController: NavHostController, showMyDialog: MutableState<Boolean>) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Recordatorios de Hoy",
            fontSize = 18.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold
        )
        OutlinedButton(
            onClick = { navController.navigate(InternalScreens.AddReminderScreen.route) },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.agregar),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp),
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text("Agregar")
        }
    }
    CardRecordatorio(showMyDialog)
    CardRecordatorio(showMyDialog)
    CardRecordatorio(showMyDialog)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun gridCards(
    navController: NavHostController,
    curr: LocalDate,
) {
    var dia = curr.dayOfMonth.toString()
    val locale = Locale("es", "ES")
    var nombreMes = curr.month.getDisplayName(TextStyle.SHORT, locale)
        .lowercase()
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(locale) else it.toString() }

    if (dia.toInt()<10){
        dia = String.format("%02d", dia.toInt())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)

    ){
        Text(
            text = "$dia $nombreMes",
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
            Spacer(Modifier.padding(7.dp))
            linea()

            Spacer(Modifier.padding(7.dp))
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
                CardCurso(navController = navController,true)
                CardCurso(navController = navController)
                CardCurso(navController = navController)
                CardCurso(navController = navController)
                CardCurso(navController = navController)
            }
        }
    }

}
@Composable
fun linea() {

    var distancia = 190 + 170*4
    Box {
        Canvas(
            modifier = Modifier
                .width(1.dp)
                .height(distancia.dp)
                .background(Color(0x99BCC1CD))
        ) {
            drawLine(
                color = Color(0x99BCC1CD),
                strokeWidth = 2f,
                start = Offset(size.width / 2, 0f),
                end = Offset(size.width / 2, size.height),
            )
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
                colors = ButtonDefaults.buttonColors(Color(0xFFC8DBF8)),
                onClick = {
                    minimize.value = !minimize.value
                },

                ) {
                Text(
                    label.value,
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}



@Composable
fun MyDialog(showMyDialog: MutableState<Boolean>) {
    if(showMyDialog.value){
        Dialog(
            onDismissRequest = { showMyDialog.value=false },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = true
            )
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 15.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.dialog_warning),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp),
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Marcar como realizado",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = "Estas a punto de marcar como realizado este recordatorio, por lo que se borrará de tu calendario. ¿Deseas continuar?",
                    fontSize = 13.sp,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(end = 20.dp, start = 20.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Row {
                    Spacer(modifier = Modifier.padding(10.dp).weight(0.3f))
                    TextButton(
                        modifier = Modifier.weight(0.25f),
                        onClick = {
                            showMyDialog.value = false
                        }
                    ) {
                        Text(
                            "Volver",
                            color = Color(0xFF495ECA)
                        )
                    }
                    FilledTonalButton(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .weight(0.45f),
                        colors = ButtonDefaults.buttonColors(Color(0xFFC8DBF8)),
                        onClick = {

                        },

                        ) {
                        Text(
                            "Continuar",
                            fontSize = 13.sp,
                            fontFamily = poppins,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    }

                }
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }

    }

}