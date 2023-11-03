package com.pruebita.mydailyfisiapp.ui.screens.attendance.student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.screens.attendance.teacher.CurseReportTeacherScreen
import com.pruebita.mydailyfisiapp.ui.screens.attendance.teacher.HeaderCurseReport
import com.pruebita.mydailyfisiapp.ui.screens.attendance.teacher.StudentRow
import com.pruebita.mydailyfisiapp.ui.screens.attendance.teacher.TableHeader
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun PreviewCurseReportStudentScreen(){

    val navController = rememberNavController()
    CurseReportStudentScreen(navController)
}
@Composable
fun CurseReportStudentScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFF))
            .padding(25.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        item {
            Column(
                modifier = Modifier.height(120.dp)
            ) {
                HeaderCurseReport(navController)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Total de asistencias: 8/16",
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
        item {
            TableWeeksHeader()

        }
        item {
            WeekRow()
            WeekRow()
            WeekRow()
            WeekRow()
            WeekRow()
            WeekRow()
            WeekRow()
            WeekRow()
        }

    }

}

@Composable
fun WeekRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            Text(
                text = "Semana 1",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF1B2128),
                )
            )

        }
        Column(
            modifier = Modifier.weight(0.25f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.calendar_check),
                contentDescription = "my posts",
                tint = Color(0xFF29D697),
            )
        }
        Column(
            modifier = Modifier.weight(0.25f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.calendar_close),
                contentDescription = "my posts",
                tint = Color(0xFFF25E56),
            )
        }
    }
    Divider()
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun TableWeeksHeader() {
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            Text(
                text = "Estudiante",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(600),
                    brush = brush,

                    )
            )
        }
        Column(
            modifier = Modifier.weight(0.25f)
        ) {
            Text(
                text = "Teoria",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(600),
                    brush = brush,

                    )
            )
        }
        Column(
            modifier = Modifier.weight(0.25f)
        ) {
            Text(
                text = "Lab",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(600),
                    brush = brush,

                    )
            )
        }


    }
    Divider()
}