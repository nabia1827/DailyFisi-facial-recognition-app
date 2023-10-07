package com.pruebita.mydailyfisiapp.ui.components.attendance

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun AttendanceLogin() {
    Box(
        contentAlignment = Alignment.TopStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.polygon_small_secundary_pastel),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds

        )

        Image(
            painter = painterResource(id = R.drawable.polygon_small_primary_gradient),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(vertical = 25.dp)
        ) {
            Row (modifier = Modifier.padding(start = 20.dp)){
                Text(
                    text = "Mis ",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppins,
                    color = Color.White
                )

            }
            Row(modifier = Modifier.padding(start = 20.dp)) {
                Text(
                    text = "Asistencias ",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))


        }
    }

}