package com.pruebita.mydailyfisiapp.ui.screens.schedule.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun CardHora(){
    var horaInicio = "12:00"
    var horaFinal = "14:40"
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