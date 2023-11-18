package com.pruebita.mydailyfisiapp.ui.screens.schedule

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.viewmodel.ScheduleViewModel
import java.util.Calendar

@Preview
@Composable
fun PreviewLocationScreen(){
    val navController = rememberNavController()
    //LocationScreen(navController, idCourse = idCourse, scheduleViewModel = scheduleViewModel)
}

@Composable
fun LocationScreen(
    navController: NavHostController,
    imageModifier: Modifier = Modifier,
    maxScale: Float = 4f,
    minScale: Float = 2.1f,
    idCourse: Int,
    scheduleViewModel: ScheduleViewModel
) {



    var scale by remember { mutableStateOf(2.1f) }
    var maxOffsetX by remember{ mutableStateOf((200 * (scale - 1)).coerceAtLeast(0f))}
    var maxOffsetY by remember{ mutableStateOf( (200 * (scale - 1.8)).coerceAtLeast(0.0))}
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }

    val context = LocalContext.current
    val drawable = context.getDrawable(R.drawable.card_location)
    val bitmap = drawable?.toBitmap()

    val drawable_icon = context.getDrawable(R.drawable.baseline_location_on_24)
    drawable_icon?.setTint(0xFFFFFFFF.toInt())
    val bitmap_icon = drawable_icon?.toBitmap(height = 25, width = 25)

    var columnHeight = remember { mutableStateOf(240) }

    var xpos = remember {
        mutableStateOf(40)
    }
    var ypos = remember {
        mutableStateOf(135)
    }
    var labelLocation = remember{
        mutableStateOf("Aula 102")
    }

    var istheoric = remember {
        mutableStateOf(true)
    }
    val courseActual = scheduleViewModel.getCourse(idCourse)

    val timeTheoryStartHour = courseActual?.theoryPart?.startHour?.let { String.format("%02d", it.get(Calendar.HOUR_OF_DAY)) }
    val timeTheoryStartMin = courseActual?.theoryPart?.startHour?.let { String.format("%02d", it.get(Calendar.MINUTE)) }
    val timeTheoryEndHour = courseActual?.theoryPart?.endHour?.let { String.format("%02d", it.get(Calendar.HOUR_OF_DAY)) }
    val timeTheoryEndMin = courseActual?.theoryPart?.endHour?.let { String.format("%02d", it.get(Calendar.MINUTE)) }
    val timeTheory = "$timeTheoryStartHour:$timeTheoryStartMin - $timeTheoryEndHour:$timeTheoryEndMin"


    val timeLaboStartHour = courseActual?.labPart?.startHour?.let { String.format("%02d", it.get(Calendar.HOUR_OF_DAY)) }
    val timeLaboStartMin = courseActual?.labPart?.startHour?.let { String.format("%02d", it.get(Calendar.MINUTE)) }
    val timeLaboEndHour = courseActual?.labPart?.endHour?.let { String.format("%02d", it.get(Calendar.HOUR_OF_DAY)) }
    val timeLaboEndMin = courseActual?.labPart?.endHour?.let { String.format("%02d", it.get(Calendar.MINUTE)) }
    val timeLabo = "$timeLaboStartHour:$timeLaboStartMin - $timeLaboEndHour:$timeLaboEndMin"

    val theoryDescription = "Aula ${courseActual?.labPart?.room?.idRoom}, ${courseActual?.labPart?.room?.floor}°piso - ${courseActual?.labPart?.room?.pavilion} Pabellon"
    val laboDescription = "Lab ${courseActual?.labPart?.room?.idRoom}, ${courseActual?.labPart?.room?.floor}°piso - ${courseActual?.labPart?.room?.pavilion} Pabellon"


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        val newScale = (scale * zoom).coerceIn(minScale, maxScale)

                        maxOffsetX = (200 * (scale - 1)).coerceAtLeast(0f)
                        maxOffsetY = (200 * (scale - 1.6)).coerceAtLeast(0.0)


                        val newOffset = Offset(
                            x = (offset.x + pan.x * scale).coerceIn(
                                -maxOffsetX.dp.toPx(),
                                maxOffsetX.dp.toPx()
                            ),
                            y = (offset.y + pan.y * scale).coerceIn(
                                -maxOffsetY.dp.toPx(),
                                maxOffsetY.dp.toPx()
                            )
                        )

                        scale = newScale
                        offset = newOffset
                    }
                }
        ){
            Image(
                painter = rememberImagePainter(data = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/maps%2Ffloors%2Ffisi_floor_2.png?alt=media&token=dca9b720-af54-4eef-94f4-36fc0cd987a7"),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
            )

            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                ZoomButton(
                    id = R.drawable.baseline_zoom_in_24 
                ) {
                    val newScale = (scale * 1.2f).coerceAtMost(maxScale)
                    scale = newScale
                }
                Spacer(modifier = Modifier.padding(8.dp))

                ZoomButton(
                    id = R.drawable.baseline_zoom_out_24
                ) {
                    val newScale = (scale * 0.8f).coerceAtLeast(minScale)
                    scale = newScale

                    val newOffset = Offset(
                        x = (offset.x).coerceIn(
                            -maxOffsetX,
                            maxOffsetX
                        ),
                        y = (offset.y).coerceIn(
                            (-maxOffsetY).toFloat(),
                            maxOffsetY.toFloat()
                        )
                    )
                    offset = newOffset
                }
            }


            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    ),
                onDraw = {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    val imageX = (canvasWidth/120)*xpos.value
                    val imageY = (canvasHeight/255)*ypos.value

                    var imageWidth = 0f
                    var imageHeight = 0f
                    bitmap?.let { image ->

                        drawImage(image.asImageBitmap(), Offset(imageX,  imageY))
                    }
                    bitmap_icon?.let { image ->
                        imageWidth = image.width.dp.toPx()
                        imageHeight = image.height.dp.toPx()
                        drawImage(image.asImageBitmap(), Offset(imageX+4*imageWidth/9,  imageY+imageHeight/16))
                    }


                    val textSize = 15.dp
                    val textColor = Color.White
                    val textPosition = Offset(imageX+3*imageWidth/14, imageY+6*imageHeight/8) // Posición del texto


                    drawIntoCanvas { canvas ->
                        val paint = Paint()
                        paint.color = textColor.toArgb()
                        paint.textSize = textSize.value
                        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)

                        canvas.nativeCanvas.drawText(
                            labelLocation.value,
                            textPosition.x,
                            textPosition.y,
                            paint
                        )
                    }
                }
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = if(ypos.value>(columnHeight.value/2))Alignment.TopCenter else Alignment.BottomEnd
            ){
                ElevatedButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 90.dp, end = 30.dp, start = 30.dp)
                        .fillMaxWidth()
                        .height(95.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent

                    ),contentPadding = PaddingValues(),
                    enabled = false,
                    shape = RoundedCornerShape(14.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        disabledElevation = 3.dp
                    )

                ) {
                    if(istheoric.value){
                        CardTeorica(theoryDescription, timeTheory)
                    }
                    else{
                        CardPractica(timeLabo, laboDescription)
                    }
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp, end = 20.dp, start = 20.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF1D93BB),
                                    Color(0xFF4579CB),
                                    Color(0xFF6C5FDA)
                                )
                            ),
                            shape = CircleShape
                        )
                        .shadow(
                            elevation = 0.dp,
                            shape = CircleShape,
                            clip = true
                        )
                        .clickable {
                            if(istheoric.value){
                                navController.popBackStack()
                            }
                            else{
                                xpos.value = 40
                                ypos.value = 135
                                istheoric.value = true
                            }
                        },
                    contentAlignment = Alignment.BottomEnd
                ){
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "volver",
                        tint = Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp, end = 20.dp, start = 20.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF1D93BB),
                                    Color(0xFF4579CB),
                                    Color(0xFF6C5FDA)
                                )
                            ),
                            shape = CircleShape
                        )
                        .shadow(
                            elevation = 0.dp,
                            shape = CircleShape,
                            clip = true
                        )
                        .clickable {
                            if(istheoric.value){
                                xpos.value = 40
                                ypos.value = 85
                                istheoric.value = false
                            }
                            else{
                                navController.popBackStack()
                            }
                        },
                    contentAlignment = Alignment.BottomEnd
                ){
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "volver",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ZoomButton(
    id: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFFC8DBF8), shape = CircleShape)
            .shadow(
                elevation = 0.dp,
                shape = CircleShape,
                clip = true
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(25.dp)

        )
    }
}