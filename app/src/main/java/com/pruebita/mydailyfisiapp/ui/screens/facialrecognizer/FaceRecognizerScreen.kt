package com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer

import android.Manifest
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Composable
fun FaceRecognizerScreen(
    navController: NavHostController,
    error: Boolean = false,
    isGoogleAccount: Boolean
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement =  Arrangement.Top
    ){
        Column(
            modifier = Modifier
                .weight(0.3f)
        ){
            HeaderRecognizer(Modifier.padding(0.dp))
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
        ){
            ContentRecognizer(error,navController, isGoogleAccount)
        }
        Column(
            modifier = Modifier
                .weight(0.1f)
        ){
            FooterRecognizer()
        }
    }
}

@Composable
fun FooterRecognizer() {
    Image(
        painter = painterResource(id = R.drawable.polygon_gradient_recognizer_footer),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillBounds
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContentRecognizer(error: Boolean, navController: NavHostController,isGoogleAccount:Boolean) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val deshabilitado = Brush.horizontalGradient(
        colors = listOf(Color(0xFFEBECF0), Color(0xFFEBECF0))
    )
    val habilitado = Brush.horizontalGradient(
        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
    )

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    var enable = permissionState.status.isGranted

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,


        ){
        Box(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            var color = Color(0xFF495ECA)
            if(error){
                color = Color(0xFFEC3773)
            }
            Text(
                text = "Miguel Perez",
                textAlign = TextAlign.Center,
                fontSize = 34.sp,
                fontFamily = poppins,
                color = color,
                fontWeight = FontWeight.SemiBold
            )
        }


        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            InicieRegistro(error)
        }
        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )

        {
            ElevatedButton(
                onClick = {
                    navController.navigate(AppScreens.RecognizingScreen.route + "/$isGoogleAccount")
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(280.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFFFFFFF),
                    disabledContainerColor = Color(0xFFB3B6C4),
                    disabledContentColor = Color(0xFFB3B6C4)


                ),
                contentPadding = PaddingValues(),
                enabled = enable

            ) {

                Box(
                    modifier =  Modifier
                        .fillMaxSize()
                        .background(
                            brush = if (enable) habilitado else deshabilitado,
                            shape = RoundedCornerShape(22.dp)
                        ),
                    contentAlignment = Alignment.Center,
                )
                {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = "Iniciar Registro", fontSize = 16.sp, fontFamily = poppins)

                    }

                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            FilledTonalButton(
                onClick = {
                    navController.navigate(AppScreens.LoginScreen.route)
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(280.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color(0xFFC8DBF8)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Regresar",
                    modifier = Modifier
                        .size(18.dp),

                    )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = "Regresar", fontSize = 16.sp, fontFamily = poppins)


            }
        }


    }

}

@Composable
fun InicieRegistro(error: Boolean) {
    var brush = Brush.horizontalGradient(listOf(Color(0xFF6663D7), Color(0xFF1E92BA)))
    var color = Color(0xFFC8DBF8)

    if(error){
        brush = Brush.horizontalGradient(listOf(Color(0xFFEC3773), Color(0xFFF25E56)))
        color = Color(0xFFF8CAD9)
    }


    Canvas(modifier = Modifier
        .height(180.dp)) {
        drawCircle(color , radius = 140.dp.toPx())
    }



    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(
                brush,
                radius = 125.dp.toPx()
            )
        }
    )
    Text(
        text = "Inicie su\n\nRegistro",
        textAlign = TextAlign.Center,
        fontSize = 34.sp,
        fontFamily = poppins,
        color = Color.White,
        fontWeight = FontWeight.SemiBold
    )


}

@Composable
fun HeaderRecognizer(modifier: Modifier) {
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
                .fillMaxWidth().fillMaxHeight(),
            contentScale = ContentScale.FillBounds
        )
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ){
            Text(
                text = "Registro de",
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontFamily = poppins,
                color = Color.White
            )

            Text(
                text = "IDENTIDAD FACIAL",
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                fontFamily = poppins,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

        }


    }
}