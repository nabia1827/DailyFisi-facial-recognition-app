package com.pruebita.mydailyfisiapp.ui.components.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Composable
fun HeaderStart(){
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.polygon_secundary_pastel),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds

        )

        Image(
            painter = painterResource(id = R.drawable.polygon_primary_gradient),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.logo_rectangulo),
                    contentDescription = null,
                    modifier = Modifier
                        .width(120.dp)

                )
                Text(
                    text = "DF",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF4977CC)
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Row {
                Text(
                    text = "Daily ",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppins,
                    color = Color.White
                )
                Text(
                    text = "FISI",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    color = Color.White
                )
            }
        }
    }
}