package com.pruebita.mydailyfisiapp.ui.screens.attendance.teacher


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.components.attendance.AttendanceLogin
import com.pruebita.mydailyfisiapp.ui.navigation.InternalScreens
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun AttendanceTeacherScreenPreview(){
    val navAttendance = rememberNavController()
    AttendanceTeacherScreen(navAttendance)
}
@Composable
fun AttendanceTeacherScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Attendance(Modifier.padding(0.dp),navController)
    }
}
@Composable
fun Attendance(modifier: Modifier,navController: NavHostController){
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top
        ){
            Column(modifier = Modifier) {
                Row(
                    modifier = Modifier
                        .weight(0.4f)
                ) {
                    AttendanceLogin()
                    Spacer(modifier = Modifier.height(30.dp))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp).weight(0.3f),
                    horizontalArrangement = Arrangement.Center, // Centra horizontalmente los elementos en la fila
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.padding(top=5.dp, bottom = 5.dp)
                            .width(321.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF6C5FDA)),
                        contentAlignment = Alignment.Center // Centra el contenido del Box
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fondo_evento_home),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                        RectanguloConTextoYBoton1("Registra la","Asistencia de Hoy",navController)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp).weight(0.3f),
                    horizontalArrangement = Arrangement.Center, // Centra horizontalmente los elementos en la fila
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.padding(top=5.dp, bottom = 5.dp)
                            .width(321.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF6C5FDA)),
                        contentAlignment = Alignment.Center // Centra el contenido del Box
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fondo_evento_home),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                        RectanguloConTextoYBoton2("Revisa los Reportes","por Cursos",navController)
                    }
                }

            }

        }

    }
}
@Composable
fun RectanguloConTextoYBoton1(text1: String, text2: String, navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart) // Alinea la primera columna en la parte inferior izquierda
                .padding(16.dp)
        ) {
            Text(
                text = text1,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = text2 ,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd) // Alinea la segunda columna en la parte superior derecha
                .padding(16.dp)
        ) {
            LoginButtonR1(navController)
        }
    }

}

@Composable
fun RectanguloConTextoYBoton2(text1: String, text2: String, navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart) // Alinea la primera columna en la parte inferior izquierda
                .padding(16.dp)
        ) {
            Text(
                text = text1,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = text2 ,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd) // Alinea la segunda columna en la parte superior derecha
                .padding(16.dp)
        ) {
            LoginButtonR2(navController)
        }
    }

}

@Composable
fun LoginButtonR1(navController: NavHostController) {
    ElevatedButton(
        onClick = {
            navController.navigate(InternalScreens.TodayAttendanceTeacherScreen.route)
        },
        modifier = Modifier
            .width(62.dp)
            .height(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color(0xFF495ECA),
            disabledContainerColor = Color(0xFFB3B6C4)

        ), contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
            ,
            contentAlignment = Alignment.Center,
        ) {
            Row {
                Box(modifier = Modifier.padding(4.dp),contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.vector_line),
                        contentDescription = "vector_line",
                        modifier = Modifier.size(12.dp, 12.dp)
                    )
                }
                Text(text = "Ir", fontSize = 13.sp, fontFamily = poppins)

            }
        }
    }
}

@Composable
fun LoginButtonR2(navController: NavHostController) {
    ElevatedButton(
        onClick = {
            navController.navigate(InternalScreens.AttendanceReportTeacherScreen.route)
        },
        modifier = Modifier
            .width(62.dp)
            .height(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color(0xFF495ECA),
            disabledContainerColor = Color(0xFFB3B6C4)

        ), contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
            ,
            contentAlignment = Alignment.Center,
        ) {
            Row {
                Box(modifier = Modifier.padding(4.dp),contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.vector_line),
                        contentDescription = "vector_line",
                        modifier = Modifier.size(12.dp, 12.dp)
                    )
                }
                Text(text = "Ir", fontSize = 13.sp, fontFamily = poppins)

            }
        }
    }
}