package com.pruebita.mydailyfisiapp.ui.screens.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import java.util.Calendar

@Preview(showBackground = true)
@Composable
fun EditReminderScreen(){
    val navController = rememberNavController()
    EditReminderScreen(navController)
}

@Composable
fun EditReminderScreen(navController: NavHostController) {
    //Variables Predefinidas
    var title_reminder = remember {
        mutableStateOf("nuevo")
    }
    val initialSelectedDay = Calendar.getInstance().apply {
        set(2023, Calendar.NOVEMBER, 22) // Establece la fecha específica que deseas (año, mes, día)
    }
    val initialstartHour = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 10)
        set(Calendar.MINUTE, 40)
    }
    val initialendHout = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 16)
        set(Calendar.MINUTE, 20)
    }



    var selectedId by rememberSaveable { mutableIntStateOf(3) }
    var selectedDate by rememberSaveable { mutableStateOf(initialSelectedDay) }
    val openDialog = remember { mutableStateOf(false) }

    var showTimePickerLeft by remember { mutableStateOf(false) }
    var showTimePickerRight by remember { mutableStateOf(false) }
    var initHour by rememberSaveable { mutableStateOf(initialstartHour) }
    var endHour by rememberSaveable { mutableStateOf(initialendHout) }



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
            HeaderEditEvent(navController)
        }
        Column (
            modifier = Modifier
                .weight(0.21f),
            verticalArrangement = Arrangement.Center
        ){
            FieldEditEvent(
                title = "Titulo de Recordatorio",
                isSingleLine = false, 3,6,
                title_reminder
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
            ButtonEditEvent(navController)
        }
    }

}

@OptIn(ExperimentalTextApi::class)
@Composable
fun HeaderEditEvent(navController:NavHostController) {
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
                text = "Editar  \nRecordatorio",
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


@Composable
fun ButtonEditEvent(navController: NavHostController) {
    ElevatedButton(
        onClick =
        {
           navController.popBackStack()
        },
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
            Text(text = "Guardar", fontSize = 16.sp, fontFamily = poppins)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldEditEvent(
    title: String,
    isSingleLine: Boolean,
    minLines: Int,
    maxLines: Int,
    title_reminder: MutableState<String>
) {
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
            value = title_reminder.value,
            onValueChange = { title_reminder.value = it },
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