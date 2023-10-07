package com.pruebita.mydailyfisiapp.ui.screens.attendance

import android.widget.Button
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.components.attendance.AttendanceLogin
import com.pruebita.mydailyfisiapp.ui.components.attendance.BottomFond
import com.pruebita.mydailyfisiapp.ui.components.attendance.BottomFond2
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun AttendanceScreenPreview(){
    val navAttendance = rememberNavController()
    AttendanceScreen(navAttendance)
}
@Composable
fun AttendanceScreen(navController: NavController){
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
fun Attendance(modifier: Modifier,navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center
        ){
            Column(modifier = Modifier.weight(0.3f)) {
                Row {
                    AttendanceLogin()
                }
                Spacer(modifier = Modifier.height(150.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center // Centra horizontalmente los elementos en la fila
                ) {
                    Box(
                        modifier = Modifier
                            .width(321.dp)
                            .height(122.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF6C5FDA)),
                        contentAlignment = Alignment.Center // Centra el contenido del Box
                    ) {
                        RectanguloConTextoYBoton("Registra tu","Asistencia de Hoy")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center // Centra horizontalmente los elementos en la fila
                ) {
                    Box(
                        modifier = Modifier
                            .width(321.dp)
                            .height(122.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF6C5FDA)),
                        contentAlignment = Alignment.Center // Centra el contenido del Box
                    ) {
                        RectanguloConTextoYBoton("Revisa los Reportes","por Cursos")
                    }
                }

            }

        }

    }
}
@Composable
fun RectanguloConTextoYBoton(text1:String,text2:String) {
    Box(
        modifier = Modifier
            .width(321.dp)
            .height(122.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF495ECA))
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
            LoginButton2()
        }
    }

}

@Composable
fun LoginButton2() {
    ElevatedButton(
        onClick = {
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
