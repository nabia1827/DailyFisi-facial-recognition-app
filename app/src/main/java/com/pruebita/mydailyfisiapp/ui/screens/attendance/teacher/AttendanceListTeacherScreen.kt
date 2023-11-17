package com.pruebita.mydailyfisiapp.ui.screens.attendance.teacher

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
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
import com.pruebita.mydailyfisiapp.viewmodel.AttendanceListTeacherViewModel

@Preview(showBackground = true)
@Composable
fun PreviewAttendanceListTeacherScreen(){
    val navController = rememberNavController()
    val viewModel:AttendanceListTeacherViewModel = hiltViewModel()
    AttendanceListTeacherScreen(navController,viewModel)
}

@Composable
fun AttendanceListTeacherScreen(navController: NavHostController,viewModel:AttendanceListTeacherViewModel) {
    val courseName: String by viewModel.courseName.observeAsState(initial = "")
    val section: Int by viewModel.section.observeAsState(initial = 0)
    val subPart: String by viewModel.subPart.observeAsState(initial = "")
    val listStudents: MutableList<StudentAssistUnit> by viewModel.listStudents.observeAsState(initial = mutableListOf())
    val listAssists: MutableList<Boolean> by viewModel.listAssists.observeAsState(initial = mutableListOf())
    val cantAssisted: Int by viewModel.cantAssisted.observeAsState(initial = 0)
    val timeRemaining: String by viewModel.timeRemaining.observeAsState(initial = "")
    val isFinished: Boolean by viewModel.isFinished.observeAsState(initial = false)

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
                modifier = Modifier.height(160.dp)
            ) {
                HeaderListScreen(navController, courseName,section,subPart)
                Spacer(modifier = Modifier.padding(7.dp))

                Row {
                    Column(
                        modifier = Modifier.weight(0.7f).fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Tiempo restante: $timeRemaining",
                            style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 36.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF495ECA),

                                )
                        )
                        Text(
                            text = "Asistentes: $cantAssisted",
                            style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 36.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF495ECA),

                                )
                        )
                    }
                    Column(
                        modifier = Modifier.weight(0.3f),
                        horizontalAlignment = Alignment.End
                    ) {
                        FinishButton(isFinished) { viewModel.endAttendance() }
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))

            }
            HeaderList()
            for (i in 0 until listStudents.size){
                StudentItem(listStudents[i],listAssists[i]) { state -> viewModel.setAttendance(i, state) }
            }
        }

    }
}

@Composable
fun FinishButton(isFinished: Boolean, endAttendance: () -> Unit) {
    val brush = Brush.verticalGradient(
        colors = listOf(Color(0xFFC8DBF8), Color(0xFFC8DBF8))
    )
    ElevatedButton(
        onClick = {endAttendance()},
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
        ,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Black,
            disabledContainerColor = Color(0xFFB3B6C4),
            disabledContentColor = if (!isFinished) Color.Black else Color(
                0xFF404650
            )

        ), contentPadding = PaddingValues(),
        enabled = !isFinished
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = brush,
                    shape = RoundedCornerShape(22.dp)
                ),
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = "Finalizar",
                fontSize = 12.sp,
                fontFamily = poppins
            )
        }


    }
}

@Composable
fun HeaderList() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.65f)
        ) {
            Text(
                text = "Estudiante",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF534D59),

                    )
            )
        }
        Column(
            modifier = Modifier.weight(0.35f)
        ) {
            Text(
                text = "Estado",
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

@Composable
fun StudentItem(student: StudentAssistUnit, assist:Boolean,setAttendance: (Boolean) -> Unit) {
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }
    ListItem(
        headlineContent = {
            Text(
                text = "${student.lastNames},",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1B2128),

                    )
            )

        },
        supportingContent = {
            Text(
                text = "${student.names}",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF959595),

                    )
            )

        },
        trailingContent = {
            AttendanceChip(assist, setAttendance)
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .background(brush = brush, shape = CircleShape)
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${student.nick}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),

                        )
                )
            }
        }
    )
    Divider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceChip(assist: Boolean, setAttendance: (Boolean) -> Unit) {

    FilterChip(
        onClick = { setAttendance(!assist)},
        label = {
            if (assist)
            {
                Text(
                    text = "Asistió",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium)
                )
            }
            else
            {
                Text(
                    text = "Falta",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium)
                )
            }
        },
        selected = assist,
        leadingIcon =
        if (!assist) {
            {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                    tint = Color(0xFF3F3748)
                )
            }

        } else {
            {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                    tint = Color(0xFF409261)
                )
            }
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color(0xFFE4E4E4),
            labelColor = Color(0xFF3F3748),
            selectedContainerColor = Color(0xFFC5F5D3),
            selectedLabelColor = Color(0xFF409261)
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color(0xFFE4E4E4),
            selectedBorderColor = Color(0xFFC5F5D3),
            borderWidth = 0.dp,
            selectedBorderWidth = 0.dp)
    )
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun HeaderListScreen(navController: NavHostController, courseName:String, section:Int, subPart:String) {
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
                text = "$courseName",
                style = TextStyle(
                    brush = brush,
                    fontSize = 34.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,

                )

            )
            Text(
                text = "Seccion $section - $subPart",
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
