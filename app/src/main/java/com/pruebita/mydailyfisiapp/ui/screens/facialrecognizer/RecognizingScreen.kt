package com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RecognizingScreen(navController: NavHostController) {
    var error by remember {
        mutableStateOf<Boolean>(false)
    }

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
            ContentRecognizing(navController,error)
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
fun Recognizing(porcentaje: MutableState<String>, navController: NavHostController, error: Boolean) {
    val animationDurationMillis = 2000 // Duración total de 2 segundos

    var nuevo = remember {
        mutableStateOf(0.0)
    }

    // Crea un estado para el progreso de la animación
    val animationProgress = remember { Animatable(0f) }

    // Crea un CoroutineScope para manejar las actualizaciones del texto porcentual
    val coroutineScope = rememberCoroutineScope()

    fun updatePercentageText() {
        coroutineScope.launch {
            repeat(4) { // Repite 4 veces (4 iteraciones)
                animationProgress.animateTo(
                    targetValue = 1f, // Avanzar al 100%
                    animationSpec = tween(durationMillis = animationDurationMillis)
                )


                // Reiniciar la animación al 0%
                if (nuevo.value != 1.0) {
                    animationProgress.snapTo(0f)
                }

                nuevo.value += 0.25
                if(nuevo.value == 0.25){
                    navController.navigate(route = AppScreens.FaceRecognizerScreen.route + "/true")
                }
            }
        }
    }

    // Mostrar la cámara al inicio
    LaunchedEffect(Unit) {
        delay(4000)
        updatePercentageText()
    }

    if (porcentaje.value == "100") {
        Canvas(modifier = Modifier
            .height(180.dp)) {
            drawCircle(Color(0xFFC8DBF8), radius = 140.dp.toPx())
        }

        LaunchedEffect(Unit) {
            delay(1500) // Ajusta el tiempo de retraso según tus necesidades (1000ms = 1 segundo)
            navController.navigate(AppScreens.MainScreen.route) // Reemplaza "nuevo_screen" con la ruta de tu destino
        }
    }

    Column(
        modifier = Modifier
            .height(250.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(190.dp))
    ) {
        Camera()
    }

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.pruebacirculo)
    )

    LottieAnimation(
        modifier = Modifier
            .fillMaxSize(),
        composition = composition,
        contentScale = ContentScale.FillWidth,
        progress = animationProgress.value // Asigna el progreso de la animación
    )

    porcentaje.value = ((nuevo.value + (animationProgress.value / 4.0)) * 100).roundToInt().toString()
}



@Composable
fun ContentRecognizing(navController: NavHostController, error: Boolean) {
    var porcentaje = remember {
        mutableStateOf("")
    }
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
            Text(
                text = "Miguel Perez",
                textAlign = TextAlign.Center,
                fontSize = 34.sp,
                fontFamily = poppins,
                color = Color(0xFF495ECA),
                fontWeight = FontWeight.SemiBold
            )
        }


        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Recognizing(porcentaje,navController,error)
        }
        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            if(porcentaje.value != "100"){
                Text(
                    text = "Registrando ...",
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontFamily = poppins,
                    color = Color(0xFF495ECA),
                    fontWeight = FontWeight.Normal
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("${porcentaje.value}%")
                        }
                    },
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp,
                    fontFamily = poppins,
                    color = Color(0xFF495ECA),
                    fontWeight = FontWeight.SemiBold
                )

            }
            else{
                Row(
                    modifier = Modifier.clickable {
                        navController.navigate(AppScreens.MainScreen.route)
                    },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Text(
                        text = "Hecho",
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        fontFamily = poppins,
                        color = Color(0xFF495ECA),
                        fontWeight = FontWeight(600)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Image(
                        painter = painterResource(id =  R.drawable.checkfat),
                        contentDescription = "CheckFat",

                        modifier = Modifier.size(40.dp, 40.dp),
                        alignment = Alignment.Center
                    )

                }

            }

        }


    }

}
