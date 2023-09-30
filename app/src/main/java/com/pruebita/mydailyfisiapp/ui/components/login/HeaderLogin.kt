package com.pruebita.mydailyfisiapp.ui.components.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun HeaderLogin() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
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
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row {
                Text(
                    text = "Iniciar ",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppins,
                    color = Color.White
                )
                Text(
                    text = "Sesion",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    color = Color.White
                )
            }
            Box(contentAlignment = Alignment.Center) {
                Icon(imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Log In Icon",
                    modifier = Modifier
                        .size(120.dp),
                    tint = Color.White
                )

            }
            Spacer(modifier = Modifier.padding(10.dp))


        }
    }

}