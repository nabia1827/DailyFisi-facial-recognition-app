package com.pruebita.mydailyfisiapp.ui.screens.attendance.teacher


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.pruebita.mydailyfisiapp.ui.screens.events.dele.EventsScreen
import com.pruebita.mydailyfisiapp.ui.theme.poppins


@Preview(showBackground = true)
@Composable
fun PreviewCurseReportTeacherScreen(){
    val navController = rememberNavController()
    CurseReportTeacherScreen(navController)
}

@Composable
fun CurseReportTeacherScreen(navController: NavHostController) {
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
                HeaderCurseReport(navController,"Algoritmica", 3)
            }
        }
        item {
            TableHeader()
        }
        item {
            StudentRow()
            StudentRow()
            StudentRow()
            StudentRow()
            StudentRow()
            StudentRow()
            StudentRow()
            StudentRow()
        }

    }

}

@Composable
fun StudentRow() {
    Row(
        modifier = Modifier.fillMaxWidth().height(70.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.5f)
        ) {
            Text(
                text = "Nuñez Zegarra,",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1B2128),

                    )
            )
            Text(
                text = "Oscar Luis",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF959595),

                    )
            )
        }
        Column(
            modifier = Modifier.weight(0.25f)
        ) {
            Text(
                text = "7/8",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF534D59),

                    )
            )
        }
        Column(
            modifier = Modifier.weight(0.25f)
        ) {
            Text(
                text = "7/8",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF534D59),

                    )
            )
        }


    }
    Divider()
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun TableHeader() {
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth().height(40.dp),
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

@OptIn(ExperimentalTextApi::class)
@Composable
fun HeaderCurseReport(navController: NavHostController, courseName:String, section:Int) {
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
                onClick = {navController.popBackStack()},
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
                text = courseName,
                style = TextStyle(
                    brush = brush,
                    fontSize = 34.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,

                    )

            )
            Text(
                text = "Seccion $section",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),

                    textAlign = TextAlign.Center,
                )
            )

        }


    }
}