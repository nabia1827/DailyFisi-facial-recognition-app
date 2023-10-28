package com.pruebita.mydailyfisiapp.ui.screens.attendance.student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.ui.screens.attendance.components.CircularCustomComponent
import com.pruebita.mydailyfisiapp.ui.screens.attendance.components.CustomComponent
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun PreviewAttedanceReportStudentScreen() {
    val navController = rememberNavController()
    AttedanceReportStudentScreen(navController)
}


@Composable
fun AttedanceReportStudentScreen(navController: NavHostController) {
    var globalValue = remember {
        mutableStateOf<Int>(70)
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFF))
            .padding(25.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {
            Column(
                modifier = Modifier.height(120.dp)
            ) {
                HeaderAttendanceReport(navController)
            }
        }
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomComponent(indicatorValue = globalValue.value)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "65/70",
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),

                                )
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Asistencias Registradas",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),

                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }

            }

        }
        item {
            CursoAsistenciasCard(80)
            CursoAsistenciasCard(75)
            CursoAsistenciasCard(30)
            CursoAsistenciasCard(80)
            CursoAsistenciasCard(75)
            CursoAsistenciasCard(30)
        }


    }

}

@Composable
fun CursoAsistenciasCard(value: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(
                        elevation = 7.dp,
                        spotColor = Color(0xC98B8BFF),
                        ambientColor = Color(0xC47697D3),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .background(shape = RoundedCornerShape(15.dp), color = Color(0xFFC8DBF8)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.7f)
                        .height(80.dp)
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Algotimica I",
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 20.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF151522),

                            )
                    )
                    Text(
                        text = "48 asitencias",
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 18.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),

                            )
                    )
                }

            }

        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .background(color = Color(0xFFC8DBF8), shape = CircleShape)
                    .padding(1.dp)
                    .width(115.dp)
                    .height(115.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularCustomComponent(
                    canvasSize = 100.dp,
                    indicatorValue = value, smallText = "",
                    backgroundIndicatorStrokeWidth = 30f,
                    foregroundIndicatorStrokeWidth = 30f,
                    bigTextFontSize = 17.sp,
                    smallTextFontSize = 2.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun HeaderAttendanceReport(navController: NavHostController) {
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier.weight(0.2f)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        brush = brush
                    )

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White,
                )

            }
        }

        Column(
            modifier = Modifier.weight(0.8f)
        ) {
            Text(
                text = "Reporte  \nAsistencias",
                style = TextStyle(
                    brush = brush,
                    fontSize = 34.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,

                    )
            )

        }


    }
}