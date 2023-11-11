package com.pruebita.mydailyfisiapp.ui.screens.attendance.student


import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.view.LifecycleCameraController
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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.screens.attendance.components.HeaderVerifying
import com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer.Camera
import com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer.FooterRecognizer
import com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer.InicieRegistro
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import com.pruebita.mydailyfisiapp.viewmodel.VerifyingIdentityStudentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewVerifyingIdentityStudentScreen(){
    val navController = rememberNavController()
    VerifyingIdentityStudentScreen(navController)
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun VerifyingIdentityStudentScreen(navController: NavHostController) {
    var state = remember {
        mutableStateOf<Boolean>(false)
    }

    var switch = remember {
        mutableStateOf<Boolean>(true)
    }


    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/predefinidos/img_verifyingposition.png"))
    }

    val verifyingIdentityStudentViewModel: VerifyingIdentityStudentViewModel = hiltViewModel()

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
            HeaderVerifying(navController, switch)
        }
        Column(
            modifier = Modifier
                .weight(0.8f)
        ){
            ContentRecognizing(navController,state,selectedImageUri, verifyingIdentityStudentViewModel,switch)
        }
        Column(
            modifier = Modifier
                .weight(0.1f)
        ){
            FooterRecognizer()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecognizingFace(
    porcentaje: MutableState<String>,
    verifyingIdentityStudentViewModel: VerifyingIdentityStudentViewModel,
    state: MutableState<Boolean>
) {
    val animationDurationMillis = 2000 // Duración total de 2 segundos

    var nuevo = remember {
        mutableStateOf(0.0)
    }


    val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }
    val lifecycle = LocalLifecycleOwner.current
    val executor = ContextCompat.getMainExecutor(context)
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

            }
        }


    }

    // Mostrar la cámara al inicio
    LaunchedEffect(Unit) {
        delay(4000)
        updatePercentageText()

        while (porcentaje.value != "100" && !state.value) {
            launch {
                state.value = verifyingIdentityStudentViewModel.takePictureToAPI(
                    cameraController,
                    executor,
                    "photo_prueba",
                    2,
                    5
                ) == true
                println("Estado:"+ state.value)
            }
            // Introduce a delay here if needed
            delay(100) // for example, wait for 100 milliseconds between each iteration
        }
        println("procentaje incrementado: " + nuevo.value)

    }

    Column(
        modifier = Modifier
            .height(250.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(190.dp))
    ) {
        Camera(cameraController, lifecycle /*, context*/)
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
fun RecognizingPosition(porcentaje2: MutableState<String>, navController: NavHostController, error: MutableState<Boolean>, selectedImageUri:Uri?) {
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
            repeat(2) { // Repite 4 veces (4 iteraciones)
                animationProgress.animateTo(
                    targetValue = 1f, // Avanzar al 100%
                    animationSpec = tween(durationMillis = animationDurationMillis)
                )


                // Reiniciar la animación al 0%
                if (nuevo.value != 1.0) {
                    animationProgress.snapTo(0f)
                }

                nuevo.value += 0.50
            }
        }
    }

    // Mostrar la cámara al inicio
    LaunchedEffect(Unit) {
        delay(2000)
        updatePercentageText()
    }

    if (porcentaje2.value == "100") {
        Canvas(modifier = Modifier
            .height(180.dp)) {
            drawCircle(Color(0xFFC8DBF8), radius = 140.dp.toPx())
        }
        LaunchedEffect(Unit) {
            delay(500) // Ajusta el tiempo de retraso según tus necesidades (1000ms = 1 segundo)
            navController.popBackStack()
        }
    }

    Column(
        modifier = Modifier
            .height(250.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(190.dp))
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(selectedImageUri)
                .build(),
            contentDescription = "This is an example image",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(190.dp)),
            contentScale = ContentScale.Crop

        )
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

    porcentaje2.value = ((nuevo.value + (animationProgress.value / 2.0)) * 100).roundToInt().toString()
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentRecognizing(
    navController: NavHostController,
    state: MutableState<Boolean>,
    img: Uri?,
    verifyingIdentityStudentViewModel: VerifyingIdentityStudentViewModel,
    switch: MutableState<Boolean>
) {
    var porcentaje = remember {
        mutableStateOf("")
    }
    var porcentaje2 = remember {
        mutableStateOf("")
    }


    var brusherror = Brush.horizontalGradient(listOf(Color(0xFFEC3773), Color(0xFFF25E56)))
    var colorfondo = Color(0xFFC8DBF8)
    var colortexto = Color(0xFF495ECA)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Box(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            if(porcentaje.value != "100"){
                RecognizingFace(
                    porcentaje,
                    verifyingIdentityStudentViewModel,
                    state
                )
            }
            else{
                if (state.value == true){
                    RecognizingPosition(porcentaje2,navController,state,img)
                }
                else{
                    switch.value = false
                    colorfondo = Color(0xFFF8CAD9)
                    colortexto = Color(0xFFED3D70)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Canvas(modifier = Modifier
                            .height(180.dp)) {
                            drawCircle(colorfondo , radius = 140.dp.toPx())
                        }

                        Canvas(
                            modifier = Modifier.size(200.dp),
                            onDraw = {
                                drawCircle(
                                    brusherror,
                                    radius = 125.dp.toPx()
                                )
                            }
                        )
                        Text(
                            text = "Identidad\n\nno válida",
                            textAlign = TextAlign.Center,
                            fontSize = 34.sp,
                            fontFamily = poppins,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            val curso = "Calculo II"
            val lugar = "Lab 04 -NP"

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Spacer(modifier = Modifier.padding(8.dp))

                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight()
                        .background(
                            colorfondo,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center

                ){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Curso: ")
                            }
                            append(curso)
                        },
                        color = colortexto
                    )
                }
                
                Spacer(modifier = Modifier.padding(8.dp))

                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight()
                        .background(
                            colorfondo,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Lugar: ")
                            }
                            append(lugar)
                        },
                        color = colortexto
                    )

                }
                Spacer(modifier = Modifier.padding(8.dp))
            }
            if(porcentaje.value != "100"){
                Text(
                    text = "Comprobando dentidad ...",
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp,
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
            else if(porcentaje2.value != "100") {
                if (state.value == true){
                    Text(
                        text = "Comprobando ubicación ...",
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        fontFamily = poppins,
                        color = Color(0xFF495ECA),
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("${porcentaje2.value}%")
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
                    Text(
                        text = "Intentelo de nuevo",
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        fontFamily = poppins,
                        color = Color(0xFF495ECA),
                        fontWeight = FontWeight.Normal
                    )
                }


            }
            else{
                Row(
                    modifier = Modifier.clickable {
                        //navController.navigate(AppScreens.MainScreen.route)
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
