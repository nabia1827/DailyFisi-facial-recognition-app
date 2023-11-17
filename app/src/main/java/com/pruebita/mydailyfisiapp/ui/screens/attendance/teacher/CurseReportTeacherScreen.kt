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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.data.model.domain.StudentAssistUnit
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import com.pruebita.mydailyfisiapp.viewmodel.CurseReportTeacherViewModel


@Preview(showBackground = true)
@Composable
fun PreviewCurseReportTeacherScreen(){
    val navController = rememberNavController()
    val viewModel:CurseReportTeacherViewModel = hiltViewModel()
    CurseReportTeacherScreen(navController,viewModel)
}

@Composable
fun CurseReportTeacherScreen(navController: NavHostController, viewModel:CurseReportTeacherViewModel) {

    val courseName: String by viewModel.courseName.observeAsState(initial = "")
    val section: Int by viewModel.section.observeAsState(initial = 0)
    val cont: Int by viewModel.cont.observeAsState(initial = 0)
    val totalClasses: Int by viewModel.totalClasses.observeAsState(initial = 0)
    val listStudents: MutableList<StudentAssistUnit> by viewModel.listStudents.observeAsState(initial = mutableListOf())
    val listTheoryAssists: MutableList<Int> by viewModel.listTheoryAssists.observeAsState(initial = mutableListOf())
    val listLabAssists: MutableList<Int> by viewModel.listLabAssists.observeAsState(initial = mutableListOf())

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
                HeaderCurseReport(navController,courseName, section)
            }
            TableHeader()
            for (i in 0 until listStudents.size){
                StudentRow(listStudents[i],listTheoryAssists[i], listLabAssists[i],totalClasses)
            }
            Text(
                text = "$cont",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black

                )
            )
        }

    }

}

@Composable
fun StudentRow(student: StudentAssistUnit, theory: Int, lab: Int, totalClasses: Int) {
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
                text = "${student.lastNames},",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1B2128),

                    )
            )
            Text(
                text = "${student.names}",
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
                text = "$theory/$totalClasses",
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
                text = "$lab/$totalClasses",
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