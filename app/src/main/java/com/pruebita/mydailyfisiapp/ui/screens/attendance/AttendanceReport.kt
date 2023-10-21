package com.pruebita.mydailyfisiapp.ui.screens.attendance

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.components.attendance.AttendanceLogin
import com.pruebita.mydailyfisiapp.ui.navigation.ItemMenu

@Preview(showBackground = true)
@Composable
fun AttendanceReportScreen(){
    val navAttendanceReport = rememberNavController()
    AttendanceReport(navAttendanceReport)
}
@Composable
fun AttendanceReport(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AttendanceReportSS(Modifier.padding(0.dp),navController)
    }
}

@Composable
fun AttendanceReportSS(modifier: Modifier,navController: NavHostController){
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top
        ){
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.padding(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(color = Color.White)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Box(modifier = Modifier
                            .padding(1.dp)
                            .clickable {
                                navController.navigate(
                                    ItemMenu.AttendanceScreen.route
                                )
                            }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_vector_array),
                                contentDescription = "ico_vector",
                                modifier = Modifier.size(16.dp, 16.dp)
                            )
                        }
                        Spacer(modifier =Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Reporte de Asistencias",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .background(color = Color.White)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ){

                        Spacer(modifier =Modifier.width(12.dp))
                        cantidadasisitencias(67,70)
                        Spacer(modifier =Modifier.width(10.dp))
                        Text(text = "Asisitencias Registradas",
                            fontSize = 14.sp)
                    }


                }
                Row(modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center)
                 {
                     SemicircularChartWithCircle(0.7f)

                }

                pr1(0.5f,38,"Algotimica I")

            }
        }

    }
}

@Composable
fun pr1(percent: Float, asistenciasActuales: Int, Curso: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), // Modificador de la otra fila
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        RectanguloConBordesRedondos(asistenciasActuales, Curso)
    }
}

@Composable
fun RectanguloConBordesRedondos(asistenciasActuales: Int,Curso: String) {
    Box (modifier = Modifier.padding(15.dp).fillMaxWidth()
        , contentAlignment = Alignment.Center){
        Box(
            modifier = Modifier
                .size(width = 318.dp, height = 80.dp)
                .background(color = Color(0xFFC8DBF8), shape = RoundedCornerShape(16.dp))
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "$Curso",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$asistenciasActuales asistencias",
                            fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp)) // Separación entre las columnas

                }
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd){
            CircularChartWithTextAndBackground(0.3f)
        }

    }

}

@Composable
fun CircularChartWithTextAndBackground(percent: Float) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.size(100.dp)
            ) {
                val center = Offset(size.width / 2, size.height / 2)
                val radius = size.minDimension / 2

                val sweepAngle = 360 * percent // Ángulo de la circunferencia

                // Dibuja el fondo del gráfico
                drawArc(
                    color = Color(0xFFFFFFFF),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = true,
                    topLeft = Offset(0f, 0f),
                    size = Size(size.width, size.height)
                )

                // Dibuja la parte que representa el porcentaje
                drawArc(
                    color = Color(0xFF1D93BB),
                    startAngle = 270f,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(0f, 0f),
                    size = Size(size.width, size.height)
                )
            }

            // Agrega un círculo blanco en el centro
            Box(
                modifier = Modifier.size(80.dp),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFFC8DBF8), CircleShape)
                )
            }

            // Agrega el texto del porcentaje en el centro
            Text(
                text = "${(percent * 100).toInt()}%",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

    }
}


@Composable
fun cantidadasisitencias(asistenciasActuales: Int, asistenciasTotales: Int) {
    Box {
        Column {
            Text(
                text = "$asistenciasActuales/$asistenciasTotales",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
@Composable
fun SemicircularChartWithCircle(percent: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(250.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.size(250.dp)
            ) {
                val center = Offset(size.width / 2, size.height / 2)
                val radius = size.minDimension / 2

                val sweepAngle = 180 * percent // Ángulo de la circunferencia

                // Dibuja el fondo del gráfico
                drawArc(
                    color = Color.Gray,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = true,
                    topLeft = Offset(0f, 0f),
                    size = Size(size.width, size.height)
                )

                // Dibuja la parte que representa el porcentaje
                drawArc(
                    color = Color(0xFF1D93BB),
                    startAngle = 180f,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    topLeft = Offset(0f, 0f),
                    size = Size(size.width, size.height)
                )
            }

            // Agrega un círculo blanco en el centro
            Box(
                modifier = Modifier.size(220.dp),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .background(Color.White, CircleShape)
                )
            }

            // Agrega el texto del porcentaje en el centro
            Text(
                text = "${(percent * 100).toInt()}%",
                fontSize = 34.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier =  Modifier.padding(bottom =50.dp)
            )

            // Agrega el texto "SEMESTRE 2023-II" debajo del porcentaje
            Text(
                text = "SEMESTRE 2023-II",
                fontSize = 12.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}

