package com.pruebita.mydailyfisiapp.ui.screens.attendance.student

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun PreviewTodayAttendanceScreen(){
    val navController = rememberNavController()
    TodayAttendanceScreen(navController)
}


@Composable
fun TodayAttendanceScreen(navController: NavHostController) {

    val brush = Brush.verticalGradient(
        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFF))
            .padding(15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        item {
            HeaderTodayAttendance(navController)
            Spacer(modifier =Modifier.height(25.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.15f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(
                        modifier = Modifier
                            .height(15.dp)
                            .width(15.dp)
                            .background(Color(0xFF495ECA), CircleShape)
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(8.dp)
                            .background(Color(0xFF495ECA))
                    ) {
                        Text(text = "")
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.85f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Inicio",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF495ECA),

                            )
                    )
                }
            }

            RowAsignature(1,false)
            RowAsignature(1,false)
            RowAsignature(1,false)
            RowAsignature(2, false)
            RowAsignature(3, true)
        }

    }

}
@Composable
fun RowAsignature(type: Int, isEnd: Boolean){
    val brush = Brush.verticalGradient(
        colors = listOf(Color(0xFF495ECA), Color(0xFF495ECA))
    )
    val brushOpen = Brush.verticalGradient(
        colors = listOf(Color(0xFF495ECA), Color(0xFF29D697))
    )
    val brushDisabled = Brush.verticalGradient(
        colors = listOf(Color(0xFFBBB8C0), Color(0xFFBBB8C0))
    )

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.15f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(type ==1){
                Box(
                    modifier = Modifier
                        .height(125.dp)
                        .width(8.dp)
                        .background(brush)
                ) {
                    Text(text = "")
                }
            }
            else if(type ==2){
                Box(
                    modifier = Modifier
                        .height(125.dp)
                        .width(8.dp)
                        .background(brushOpen)
                ) {
                    Text(text = "")
                }
            }
            else{
                Box(
                    modifier = Modifier
                        .height(125.dp)
                        .width(4.dp)
                        .background(brushDisabled)
                ) {
                    Text(text = "")
                }
            }


        }
        Column(
            modifier = Modifier.weight(0.85f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardAsignature(type)
        }

    }
    if(isEnd){
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.15f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(
                    modifier = Modifier
                        .height(15.dp)
                        .width(15.dp)
                        .background(
                            if (type == 1)
                                Color(0xFF495ECA)
                            else if (type == 2)
                                Color(0xFF29D697)
                            else
                                Color(0xFFBBB8C0), CircleShape
                        )
                ) {

                }

            }
            Column(
                modifier = Modifier
                    .weight(0.85f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Fin",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        color = if(type == 1)
                            Color(0xFF495ECA)
                        else if(type == 2)
                            Color(0xFF29D697)
                        else
                            Color(0xFFBBB8C0),
                        )
                )
            }
        }
    }

}

@Composable
fun CardAsignature(type: Int) {
    val brush = Brush.verticalGradient(
        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
    )
    val brushOpen = Brush.verticalGradient(
        colors = listOf(Color(0xFF29D697), Color(0xFF29D697))
    )
    val brushDisabled = Brush.verticalGradient(
        colors = listOf(Color(0xFFBBB8C0), Color(0xFFBBB8C0))
    )


//125
    FilledTonalButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp),
        onClick = {

        },
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation =ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        ),
        contentPadding = PaddingValues()

    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(15.dp)
                    .background(
                        brush = if (type == 1) brush else if (type == 2) brushOpen else brushDisabled,
                    ),
            ) {
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Calculo II :",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),

                            )
                    )
                    Text(
                        text = " Parte Teórica ",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF000000),

                            )
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.weight(0.6f),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_access_time_24),
                            contentDescription = "clock",
                            tint = Color.Black,

                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "12:00 - 13:30",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000)

                            )
                        )
                    }
                    Row(
                        modifier = Modifier.weight(0.4f)
                    ) {
                        ElevatedButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp)
                                .fillMaxWidth()
                                .height(30.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color(0xFFFFFFFF),
                                disabledContainerColor = Color(0xFFB3B6C4),
                                disabledContentColor = if (type != 3) Color.White else Color(
                                    0xFF404650
                                )

                            ), contentPadding = PaddingValues(),
                            enabled = if(type == 2) true else false
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = if (type == 1) brush else if (type == 2) brushOpen else brushDisabled,
                                        shape = RoundedCornerShape(22.dp)
                                    ),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = if(type == 1) "Marcado" else if(type == 2) "Marcar" else "Marcar",
                                    fontSize = 12.sp,
                                    fontFamily = poppins
                                )
                            }
                        }

                    }
                }
            }

        }
    }
    Spacer(modifier =Modifier.height(20.dp))
}


@Composable
fun HeaderTodayAttendance(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(0.15f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "User",
                    tint = Color.Black
                )
            }

        }
        Column(
            modifier = Modifier.weight(0.50f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier= Modifier
                        .weight(0.3f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "06",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),

                            )
                    )
                }
                Column(
                    modifier= Modifier
                        .weight(0.7f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Miercoles",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF000000),

                            )
                    )
                    Text(
                        text = "Septiembre 2023",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFBCC1CD),

                            )
                    )

                }
            }

        }
        Column(
            modifier = Modifier.weight(0.35f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledTonalButton(
                modifier = Modifier
                    .padding(end = 5.dp)
                    .fillMaxWidth(),
                onClick = {

                },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Color(0xFFC8DBF8)
                )

            ) {
                Text(
                    "Hoy",
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium
                )
            }
        }


    }
}
