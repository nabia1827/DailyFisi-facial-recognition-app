package com.pruebita.mydailyfisiapp.ui.screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
@Preview(showBackground = true)
@Composable
fun PreviewAddReminderScreen(){
    val navController = rememberNavController()
    AddReminderScreen(navController)
}


@Composable
fun AddReminderScreen(navController: NavHostController) {
    var selectedId by rememberSaveable { mutableIntStateOf(0) }
    var selectedDate by rememberSaveable { mutableStateOf(Calendar.getInstance()) }
    val openDialog = remember { mutableStateOf(false) }

    var showTimePickerLeft by remember { mutableStateOf(false) }
    var showTimePickerRight by remember { mutableStateOf(false) }
    var initHour by rememberSaveable { mutableStateOf(Calendar.getInstance()) }
    var endHour by rememberSaveable { mutableStateOf(Calendar.getInstance()) }


    MyDatePickerDialog({ openDialog.value }, { newValue: Boolean ->
        openDialog.value = newValue
    }) { newId: Int, newDate: Calendar ->
        selectedId = newId
        selectedDate = newDate
    }

    MyTimePickerLeft({newValue: Calendar ->
        initHour = newValue
    },{ showTimePickerLeft }) { newValue: Boolean ->
        showTimePickerLeft = newValue
    }
    MyTimePickerRight({newValue: Calendar ->
        endHour = newValue
    },{ showTimePickerRight }) { newValue: Boolean ->
        showTimePickerRight = newValue
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFF))
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column (
            modifier = Modifier
                .weight(0.19f)
        ){
            HeaderAddEvent(navController)
        }
        Column (
            modifier = Modifier
                .weight(0.21f),
            verticalArrangement = Arrangement.Center
        ){
            FieldAddEvent(
                title = "Titulo de Recordatorio",
                isSingleLine = false, 3,6
            )
        }
        Column (
            modifier = Modifier
                .weight(0.4f),
            verticalArrangement = Arrangement.Center
        ){
            SelectorFecha(
                { selectedId },
                { selectedDate },
                { newId: Int, newDate: Calendar ->
                    selectedId = newId
                    selectedDate = newDate
                }) { newValue: Boolean ->
                openDialog.value = newValue
            }
        }
        Column (
            modifier = Modifier
                .weight(0.2f)
        ){
            SelectorHora({ newValue: Boolean ->
                showTimePickerLeft = newValue
            },{ newValue: Boolean ->
                showTimePickerRight = newValue
            },{ initHour }) { endHour }
        }
        Column (
            modifier = Modifier
                .weight(0.2f),
            verticalArrangement = Arrangement.Center
        ){
            ButtonAddEvent()
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun MyTimePickerRight(changeEndHour: (Calendar) -> Unit, getShowTimePicker: () -> Boolean, changeShow: (Boolean) -> Unit) {
    val state = rememberTimePickerState()
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }

    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }

    if (getShowTimePicker()) {
        AlertDialog(
            onDismissRequest = { changeShow(false) },
            confirmButton = {

                TextButton(
                    onClick = {
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.HOUR_OF_DAY, state.hour)
                        cal.set(Calendar.MINUTE, state.minute)
                        cal.isLenient = false
                        changeEndHour(cal)
                        changeShow(false)
                    },
                    enabled = true
                ) {
                    Text(
                        text = "OK",
                        style = TextStyle(
                            brush = brush,
                            fontSize = 14.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium

                        )
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        changeShow(false)
                    },
                    enabled = true
                ) {
                    Text(
                        text = "Cancelar",
                        style = TextStyle(
                            brush = brush,
                            fontSize = 14.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium

                        )
                    )
                }
            },
            containerColor = Color.White,
            text = {
                TimePicker(
                    state = state,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = Color(0xCDC8DBF8),
                        clockDialSelectedContentColor = Color.White,
                        selectorColor = Color(0xFF495ECA),
                        periodSelectorBorderColor = Color(0xFF495ECA),
                        periodSelectorSelectedContainerColor = Color(0xFFC8DBF8),
                        timeSelectorSelectedContainerColor = Color(0xFF495ECA),
                        timeSelectorUnselectedContainerColor = Color(0xFFF1F5F9),
                        timeSelectorSelectedContentColor = Color.White


                    )
                )
            }

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun MyTimePickerLeft(changeInitHour: (Calendar) -> Unit,getShowTimePicker: () -> Boolean, changeShow: (Boolean) -> Unit) {
    val state = rememberTimePickerState()

    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }

    if (getShowTimePicker()) {
        AlertDialog(
            onDismissRequest = { changeShow(false) },
            confirmButton = {

                TextButton(
                    onClick = {
                        val cal = Calendar.getInstance()
                        cal.set(Calendar.HOUR_OF_DAY, state.hour)
                        cal.set(Calendar.MINUTE, state.minute)
                        cal.isLenient = false
                        changeInitHour(cal)
                        changeShow(false)
                    },
                    enabled = true
                ) {
                    Text(
                        text = "OK",
                        style = TextStyle(
                            brush = brush,
                            fontSize = 14.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium

                        )
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        changeShow(false)
                    },
                    enabled = true
                ) {
                    Text(
                        text = "Cancelar",
                        style = TextStyle(
                            brush = brush,
                            fontSize = 14.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium

                        )
                    )
                }
            },
            containerColor = Color.White,
            text = {
                TimePicker(
                    state = state,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = Color(0xCDC8DBF8),
                        clockDialSelectedContentColor = Color.White,
                        selectorColor = Color(0xFF495ECA),
                        periodSelectorBorderColor = Color(0xFF495ECA),
                        periodSelectorSelectedContainerColor = Color(0xFFC8DBF8),
                        timeSelectorSelectedContainerColor = Color(0xFF495ECA),
                        timeSelectorUnselectedContainerColor = Color(0xFFF1F5F9),
                        timeSelectorSelectedContentColor = Color.White


                    )
                )
            }

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTextApi::class)
@Composable
fun MyDatePickerDialog(
    getOpenDialog: () -> Boolean,
    changeOpenDialog: (Boolean) -> Unit,
    changeSelection: (Int, Calendar) -> Unit
) {
    var selectedDatePicker by rememberSaveable { mutableStateOf(Calendar.getInstance()) }

    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }



    if (getOpenDialog()) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled =
            remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(
            onDismissRequest = {
                changeOpenDialog(false)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        changeOpenDialog(false)
                        selectedDatePicker.timeInMillis =
                            datePickerState.selectedDateMillis ?: 1578096000000
                        changeSelection(3, selectedDatePicker)
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text(
                        text = "OK",
                        style = TextStyle(
                            brush = brush,
                            fontSize = 14.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium

                        )
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        changeOpenDialog(false)
                    }
                ) {
                    Text(
                        text = "Cancelar",
                        style = TextStyle(
                            brush = brush,
                            fontSize = 14.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium

                        )
                    )
                }
            },
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                selectedDayContainerColor = Color(0xFF495ECA),
                todayContentColor = Color(0xFF495ECA),
                todayDateBorderColor = Color(0xFF495ECA),

                )
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    selectedDayContainerColor = Color(0xFF495ECA),
                    todayContentColor = Color(0xFF495ECA),
                    todayDateBorderColor = Color(0xFF495ECA),

                    )
            )
        }
    }
}

@Composable
fun SelectorFecha(
    getSelectedId: () -> Int,
    getSelectedDate: () -> Calendar,
    changeSelection: (Int, Calendar) -> Unit,
    changeOpenDialog: (Boolean) -> Unit
) {

    val todayDate = Calendar.getInstance()
    val sdf = SimpleDateFormat("MMM dd, yyyy")

    // Tomorrow
    val tomorrow = Calendar.getInstance()
    tomorrow.add(Calendar.DAY_OF_MONTH, 1)

    // Day after tomorrow
    val afterTomorrow = Calendar.getInstance()
    afterTomorrow.add(Calendar.DAY_OF_MONTH, 2)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 15.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start

        ) {
            Text(
                text = "Selecciona una fecha",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.evento),
                contentDescription = "clock",
                tint = Color(0xFF495ECA),
                modifier = Modifier.padding(7.dp)

            )
            Text(
                text = "${sdf.format(getSelectedDate().time)}",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF495ECA),

                    ),
                modifier = Modifier.padding(7.dp)
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.25f)
                    .height(150.dp)

            ) {
                ButtonDia(0, todayDate, getSelectedId, changeSelection)
            }
            Column(
                modifier = Modifier
                    .weight(0.25f)
                    .height(150.dp)
            ) {
                ButtonDia(1, tomorrow, getSelectedId, changeSelection)

            }
            Column(
                modifier = Modifier
                    .weight(0.25f)
                    .height(150.dp)
            ) {
                ButtonDia(2, afterTomorrow, getSelectedId, changeSelection)
            }
            Column(
                modifier = Modifier
                    .weight(0.25f)
                    .height(150.dp)
            ) {
                ButtonOther(3, getSelectedId, changeOpenDialog, changeSelection)

            }


        }
    }

}

@Composable
fun ButtonOther(
    id: Int,
    getSelectedId: () -> Int,
    changeOpenDialog: (Boolean) -> Unit,
    changeSelection: (Int, Calendar) -> Unit,
) {
    var optional by remember { mutableStateOf(Calendar.getInstance()) }
    val brushSelected = Brush.verticalGradient(
        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
    )

    val brushNotSelected = Brush.verticalGradient(
        colors = listOf(Color(0xFFF1F5F9), Color(0xFFF1F5F9))
    )
    ElevatedButton(
        onClick = {
            changeOpenDialog(true)
            changeSelection(id, optional)
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if (id == getSelectedId()) Color(0xFFFFFFFF) else Color(0xFF4A4A4A),
            disabledContainerColor = Color(0xFFB3B6C4)

        ), contentPadding = PaddingValues(), //Es soluuu no tocar
        enabled = true,
        shape = RoundedCornerShape(14.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 3.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )

    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = if (id == getSelectedId()) brushSelected else brushNotSelected

                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "+",
                style = TextStyle(
                    fontSize = 25.sp,
                    lineHeight = 26.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = if (id == getSelectedId()) Color(0xFFFFFFFF) else Color(0xFF4A4A4A),

                    textAlign = TextAlign.Center,
                    letterSpacing = 0.3.sp,
                )
            )
            Text(text = "Other", fontSize = 16.sp, fontFamily = poppins)
        }

    }
}

@Composable
fun ButtonDia(
    id: Int,
    date: Calendar,
    getSelectedId: () -> Int,
    changeSelection: (Int, Calendar) -> Unit
) {
    val brushSelected = Brush.verticalGradient(
        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
    )

    val brushNotSelected = Brush.verticalGradient(
        colors = listOf(Color(0xFFF1F5F9), Color(0xFFF1F5F9))
    )
    var day by remember { mutableStateOf(date.get(Calendar.DAY_OF_MONTH)) }
    var dayOfWeek by remember { mutableStateOf(date.get(Calendar.DAY_OF_WEEK)) }

    ElevatedButton(
        onClick = {
            if (id != getSelectedId()) {
                changeSelection(id, date)

            }

        },
        modifier = Modifier
            .fillMaxSize()
            .padding(7.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if (id == getSelectedId()) Color(0xFFFFFFFF) else Color(0xFF4A4A4A),
            disabledContainerColor = Color(0xFFB3B6C4)

        ), contentPadding = PaddingValues(), //Es soluuu no tocar
        enabled = true,
        shape = RoundedCornerShape(14.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 3.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )

    ) {
        Column(
            modifier = Modifier
                .background(
                    brush = if (id == getSelectedId()) brushSelected else brushNotSelected

                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$day",
                style = TextStyle(
                    fontSize = 25.sp,
                    lineHeight = 26.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = if (id == getSelectedId()) Color(0xFFFFFFFF) else Color(0xFF4A4A4A),

                    textAlign = TextAlign.Center,
                    letterSpacing = 0.3.sp,
                )
            )
            Text(text = "${getNameDayofWeek(dayOfWeek)}", fontSize = 16.sp, fontFamily = poppins)
        }

    }
}

@Composable
fun SelectorHora(
    changeShowLeft: (Boolean) -> Unit,
    changeShowRight: (Boolean) -> Unit,
    getInitHour: () -> Calendar,
    getEndHour: () -> Calendar
) {
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start

        ) {
            Text(
                text = "Selecciona una hora",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .background(
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start

        ) {
            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .fillMaxHeight()
                    .clickable { changeShowLeft(true) },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Desde",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 26.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF94A3B8),

                        letterSpacing = 0.3.sp,
                    )
                )
                Text(
                    text = "${formatter.format(getInitHour().time)}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 26.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF1E293B),

                        letterSpacing = 0.3.sp,
                    )
                )


            }
            Column(
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "back",
                    tint = Color.Black,
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .fillMaxHeight()
                    .clickable { changeShowRight(true) },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hasta",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 26.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF94A3B8),

                        letterSpacing = 0.3.sp,
                    )
                )
                Text(
                    text ="${formatter.format(getEndHour().time)}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 26.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF1E293B),

                        letterSpacing = 0.3.sp,
                    )
                )
            }
        }
    }


}

@Composable
fun ButtonAddEvent() {
    ElevatedButton(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color(0xFFB3B6C4)

        ), contentPadding = PaddingValues(),
        enabled = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                    ),
                    shape = RoundedCornerShape(22.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Crear Evento", fontSize = 16.sp, fontFamily = poppins)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldAddEvent(title: String, isSingleLine: Boolean, minLines: Int, maxLines: Int) {
    var text by remember { mutableStateOf("") }
    Column(

    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
            )

        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            enabled = true,
            textStyle = TextStyle(
                fontSize = 13.sp,
                fontFamily = poppins,
                fontWeight = FontWeight(400),
                color = Color.Black,

                textAlign = TextAlign.Start,
            ),
            placeholder = {
                Text(
                    text = "",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF49454F),
                        textAlign = TextAlign.Start,
                    )
                )
            },
            singleLine = isSingleLine,
            minLines = minLines,
            maxLines = maxLines,
            shape = RoundedCornerShape(size = 14.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFFF1F5F9),
                unfocusedBorderColor = Color(0xFFF1F5F9),
                focusedBorderColor = Color(0xFF495ECA),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,

                ),

            )
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun HeaderAddEvent(navController:NavHostController) {
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier.weight(0.2f)
        ) {
            IconButton(
                onClick = {navController.popBackStack() },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        brush = brush
                    )

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White,
                )

            }
        }

        Column(
            modifier = Modifier.weight(0.8f)
        ) {
            Text(
                text = "Nuevo  \nRecordatorio",
                style = TextStyle(
                    brush = brush,
                    fontSize = 34.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,

                    )
            )

        }


    }
}

fun getNameDayofWeek(numeroDia: Int): String {
    val names = arrayOf("Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb")
    return names.getOrNull(numeroDia - 1) ?: " "
}