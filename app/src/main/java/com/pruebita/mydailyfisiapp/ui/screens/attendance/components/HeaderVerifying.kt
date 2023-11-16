package com.pruebita.mydailyfisiapp.ui.screens.attendance.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun HeaderVerifyingPreview(){
    val navController = rememberNavController()
    HeaderVerifying(navController, null)
}

@Composable
fun HeaderVerifying(navController: NavHostController, state: MutableState<Boolean>?) {
    var textHeader = ""

    if (state != null) {
        if(state.value){
            textHeader = "Registrando mi\n" + "asistencia..."
        }
        else{
            textHeader = "Asistencia no\n Registrada"
        }
    }

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.polygon_pastel_recognizer),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.polygon_gradient_recognizer),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ){
            Text(
                text = textHeader,
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontFamily = poppins,
                color = Color.White
            )


        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(onClick = { navController.popBackStack()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White,
                )
                
            }

        }


    }
}