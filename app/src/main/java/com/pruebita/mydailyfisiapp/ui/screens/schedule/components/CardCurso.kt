package com.pruebita.mydailyfisiapp.ui.screens.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.data.model.domain.Course
import com.pruebita.mydailyfisiapp.ui.navigation.InternalScreens
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun PreviewCardCurso(){
    val navController = rememberNavController()
    CardCurso(Course(), navController, false)
}

@Composable
fun CardCurso(
    course: Course,
    navController: NavHostController,
    isActual: Boolean,
){

    val curso = course.courseName
    val seccion = "Seccion: ${course.section}"
    val teoria = "Aula ${course.theoryPart.room.idRoom} ${course.theoryPart.room.pavilion}"

    var labo = ""
    var isLabo = false

    val docente = course.teacherFullName

    if(course.labPart!=null){
        labo = "Lab ${course.labPart.room.idRoom} ${course.labPart.room.pavilion}"
        isLabo = true
    }

    var colorCampos = if(isActual) Color.White else Color.Black
    var pixel = 40
    var expanded by remember { mutableStateOf(false) }


    val otros = Brush.horizontalGradient(
        colors = listOf(Color(0xFFC8DBF8), Color(0xFFC8DBF8))
    )
    val actual = Brush.verticalGradient(
        colors = listOf(Color(0xFF1D93BB), Color(0xFF4579CB), Color(0xFF6C5FDA))
    )

    Box (
        modifier = Modifier
            .wrapContentWidth()
            .height(170.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable { navController.navigate(InternalScreens.HorarioScreen.route + "/${course.idCourse}") }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = if (isActual) actual else otros)
                .padding(15.dp)

        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f)
            ){
                Box(
                    modifier = Modifier
                        .weight(0.9f)
                ){
                    if (curso != null) {
                        Text(
                            text = curso,
                            color = if(isActual) Color.White else Color(0xFF495ECA),
                            fontSize = 16.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(0.1f),
                    contentAlignment = Alignment.Center

                ){

                    IconButton(onClick = { expanded = true })
                    {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null, // Puedes proporcionar una descripción opcional
                            tint = Color.White, // Color del icono
                            modifier = Modifier.wrapContentSize() // Tamaño del icono
                        )
                    }

                    if(isActual){
                        pixel = 80
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        offset = DpOffset(x = 0.dp, y = 2.dp),
                        modifier = Modifier
                            .background(Color.White)
                            .align(Alignment.BottomEnd)
                            .height(pixel.dp),
                    ){
                        DropdownMenuItem(
                            modifier = Modifier.weight(0.5f),
                            text = {
                                Text(
                                    text = "Ver ubicacion",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontFamily = poppins,
                                        fontWeight = FontWeight.Normal,
                                        color = Color(0xFF000000),
                                    )
                                )

                            },
                            onClick = {
                                expanded = false
                                navController.navigate(InternalScreens.HorarioScreen.route + "/${course.idCourse}")
                            },
                        )
                        if(isActual){
                            DropdownMenuItem(
                                modifier = Modifier.weight(0.5f),
                                text = {
                                    Text(
                                        text = "Marcar Asistencia",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontFamily = poppins,
                                            fontWeight = FontWeight.Normal,
                                            color = Color(0xFF000000),
                                        )
                                    )
                                },
                                onClick = {
                                    expanded = false
                                    navController.navigate(InternalScreens.TodayAttendanceStudentScreen.route)
                                          },
                            )

                        }
                    }

                }

            }

            Text(
                text = seccion,
                color = colorCampos,
                fontSize = 13.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .weight(0.25f)
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = colorCampos,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.padding(5.dp))
                Text(
                    text ="T: $teoria",
                    color = colorCampos,
                    fontSize = 12.sp,
                    fontFamily = poppins,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                )
                if (isLabo){
                    Spacer(Modifier.padding(5.dp))
                    Text(
                        text ="P: $labo",
                        color = colorCampos,
                        fontSize = 12.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_outline_24),
                    contentDescription = null,
                    tint = colorCampos,
                    modifier = Modifier
                            .size(18.dp),
                )
                Spacer(Modifier.padding(5.dp))
                Text(
                    text ="Prof. $docente",
                    color = colorCampos,
                    fontSize = 12.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal
                )
            }

        }


    }

}