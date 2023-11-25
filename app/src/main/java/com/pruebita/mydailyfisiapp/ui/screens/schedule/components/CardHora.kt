package com.pruebita.mydailyfisiapp.ui.screens.schedule.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import java.util.Calendar

@Composable
fun CardHora(course: Course) {
    var horaInicio = "${String.format("%02d",course.theoryPart.startHour?.get(Calendar.HOUR_OF_DAY))}:${String.format("%02d",course.theoryPart.startHour?.get(Calendar.MINUTE))}"
    var horaFinal = "${String.format("%02d",course.labPart.endHour?.get(Calendar.HOUR_OF_DAY))}:${String.format("%02d",course.labPart.endHour?.get(Calendar.MINUTE))}"
    Column (
        modifier = Modifier
            .wrapContentWidth()
            .height(170.dp)
            .padding(10.dp)
    ){
        Text(
            text = horaInicio,
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Medium,

        )
        Text(
            text= horaFinal,
            color = Color(0xFFBCC1CD),
            fontSize = 16.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Medium,
        )
    }
}