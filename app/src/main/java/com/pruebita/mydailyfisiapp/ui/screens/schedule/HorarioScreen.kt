package com.pruebita.mydailyfisiapp.ui.screens.schedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.navigation.InternalScreens
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import com.pruebita.mydailyfisiapp.viewmodel.ScheduleViewModel
import java.util.Calendar

@Preview
@Composable
fun PreviewHorarioScreen(){
    val navController = rememberNavController()
    //HorarioScreen(navController, idCourse)
}
@Composable
fun HorarioScreen(
    navController: NavHostController,
    idCourse: Int,
    scheduleViewModel: ScheduleViewModel
) {
    val courseActual = scheduleViewModel.getCourse(idCourse)
    val currentWeek: Int by scheduleViewModel.currentWeek.observeAsState(initial = -1)


    val courseName = courseActual?.courseName
    val generalDescription = "Seccion ${courseActual?.section} - Semana $currentWeek"
    val teacher = "${courseActual?.teacherFullName}"

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
            HeaderHorario(navController, generalDescription, courseName, teacher)
        }
        Column(
            modifier = Modifier
                .weight(0.35f)
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.Center
        ){
            ElevatedButton(
                onClick = {

                },
                modifier = Modifier
                    .heightIn(min = 0.dp, max = 95.dp)
                    .padding(top = 10.dp, bottom = 10.dp, end = 30.dp, start = 30.dp)
                    .fillMaxWidth()
                    .weight(0.5f),
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
                CardTeorica(theoryDescription, timeTheory)
            }
            ElevatedButton(
                onClick = {

                },
                modifier = Modifier
                    .heightIn(min = 0.dp, max = 95.dp)
                    .padding(top = 10.dp, bottom = 10.dp, end = 30.dp, start = 30.dp)
                    .fillMaxWidth()
                    .weight(0.5f),
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
                CardPractica(timeLabo, laboDescription)
            }
        }
        Column(
            modifier = Modifier
                .weight(0.35f)
                .padding(top = 10.dp)
        ){
            ElevatedButton(
                onClick = {

                },
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 30.dp, start = 30.dp)
                    .fillMaxSize(),
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
                Mapa(navController, idCourse)
            }

        }
    }
}

@Composable
fun Mapa(navController: NavHostController, idCourse: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFCDCED1)),
        contentAlignment = Alignment.BottomEnd

    ) {

        Image(
            painter =  rememberAsyncImagePainter(model = "https://firebasestorage.googleapis.com/v0/b/dailyfisiapp.appspot.com/o/maps%2Fdefaults%2Floading.png?alt=media&token=c0b51c03-2f73-4226-b0fe-6d4540b3e2d9"),
            contentDescription = null, // Proporciona una descripción significativa si es necesario
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x4D000000)),
            contentAlignment = Alignment.Center
        ){

            Image(
                painter = painterResource(id = R.drawable.location_map),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(105.dp)
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp, end = 20.dp, start = 20.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF1D93BB), Color(0xFF4579CB), Color(0xFF6C5FDA))
                    ),
                    shape = CircleShape
                ).shadow(
                    elevation = 0.dp,
                    shape = CircleShape,
                    clip = true
                )
                .clickable {
                    navController.navigate(InternalScreens.LocationScreen.route + "/$idCourse")

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


@Composable
fun CardPractica(timeLabo: String, laboDescription: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFC8DBF8)),
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF1D93BB), Color(0xFF4579CB), Color(0xFF6C5FDA))
                        ),
                        shape = RoundedCornerShape(topStart = 16.dp)
                    )
                    .weight(0.18f)
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.labo),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(25.dp)
                )

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Column(
                modifier = Modifier
                    .weight(0.82f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Parte Practica",
                    fontFamily = poppins,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text = laboDescription,
                        fontFamily = poppins,
                        fontSize = 15.sp,
                        color = Color.Black,
                        modifier = Modifier.wrapContentHeight()
                    )
                }

            }

            Spacer(modifier = Modifier.padding(5.dp))
        }

        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ){
            Image(
                painter = painterResource(id = R.drawable.hora),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(30.dp)
                    .padding(0.dp),
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp)
                    .padding(5.dp),
                verticalAlignment = Alignment.Top
            ){
                Image(
                    painter = painterResource(id = R.drawable.baseline_access_time_filled_24),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .padding(top = 1.dp)
                        .size(15.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = timeLabo,
                    fontFamily = poppins,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
                )

            }


        }
    }
}

@Composable
fun CardTeorica(theoryDescription: String, timeTheory: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFC8DBF8)),
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.padding(10.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF1D93BB), Color(0xFF4579CB), Color(0xFF6C5FDA))
                        ),
                        shape = RoundedCornerShape(topStart = 16.dp)
                    )
                    .weight(0.18f)
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.teoria),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(25.dp)
                )

            }
            Spacer(modifier = Modifier.padding(5.dp))
            Column(
                modifier = Modifier
                    .weight(0.82f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Parte Teorica",
                    fontFamily = poppins,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text = theoryDescription,
                        fontFamily = poppins,
                        fontSize = 15.sp,
                        color = Color.Black,
                        modifier = Modifier.wrapContentHeight()
                    )
                }

            }

            Spacer(modifier = Modifier.padding(5.dp))
        }
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ){
            Image(
                painter = painterResource(id = R.drawable.hora),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(30.dp)
                    .padding(0.dp),
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .width(120.dp)
                    .height(40.dp)
                    .padding(5.dp),
                verticalAlignment = Alignment.Top
            ){
                Image(
                    painter = painterResource(id = R.drawable.baseline_access_time_filled_24),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .padding(top = 1.dp)
                        .size(15.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = timeTheory,
                    fontFamily = poppins,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    modifier = Modifier.
                        wrapContentHeight(align = Alignment.CenterVertically),
                    style = TextStyle(textDirection = TextDirection.Rtl)
                )

            }


        }
    }
}

@Composable
fun HeaderHorario(
    navController: NavHostController,
    generalDescription: String,
    courseName: String?,
    teacher: String
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.polygon_pastel_recognizer),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.FillBounds
        )
        Image(
            painter = painterResource(id = R.drawable.polygon_gradient_recognizer),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.FillBounds
        )
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer(modifier = Modifier.padding(5.dp))
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 15.dp)

                ){
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_vector_array),
                            contentDescription = null,
                            modifier = Modifier
                                .size(18.dp),
                            tint = Color.White
                        )
                    }

                }
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (courseName != null) {
                        Text(
                            text = courseName,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            fontFamily = poppins,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Text(
                        text = generalDescription,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontFamily = poppins,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Divider(
                        modifier = Modifier.width(210.dp),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_person_outline_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(18.dp),
                        )

                        Spacer(modifier = Modifier.padding(3.dp))

                        Text(
                            text = teacher,
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            fontFamily = poppins,
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }


        }
    }
}