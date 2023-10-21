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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.navigation.ItemMenu

@Preview(showBackground = true)
@Composable
fun  AttendanceTeacherPreview(){
    val navController = rememberNavController()
    AttendanceTeacherScreen(navController)
}
@Composable
fun  AttendanceTeacherScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AttendanceTeacherSS(Modifier.padding(0.dp),navController)
    }
}
@Composable
fun AttendanceTeacherSS(modifier: Modifier,navController: NavHostController){

    Box(modifier = Modifier.fillMaxSize()){
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
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(
                    verticalAlignment = CenterVertically
                ){
                    Box(modifier = Modifier
                        .padding(1.dp)
                        .clickable { navController.navigate(ItemMenu.AttendanceScreen.route) }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_vector_array),
                            contentDescription = "ico_vector",
                            modifier = Modifier.size(16.dp, 16.dp)
                        )
                    }
                    Spacer(modifier =Modifier.width(12.dp))

                    Column {
                        Row {
                            Text(
                                text = "Algoritmica I: Seccion 3",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                Spacer(modifier =Modifier.width(60.dp))




            }
            Row(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(horizontal = 40.dp),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Parte Teorica",
                    fontSize = 16.sp
                    )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(40.dp))
                Text(text = "Estudiante",fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.width(120.dp))
                Text(text = "Estado",fontSize = 14.sp, fontWeight = FontWeight.SemiBold)

            }
            Row {
                RowWithCircleAndRectangle()
            }

    }
}
}
@Composable
fun RowWithCircleAndRectangle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(horizontal =  23.dp))
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color(0xFF1D93BB), shape = CircleShape)
        ) {
            // Círculo pequeño
            Text(
                text = "ON",
                color= Color(0xFFFFFFFF),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.padding(horizontal =  5.dp))
        Column(
        ) {
            Text(
                text = "Nuñez Zegarra,",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = "Oscar Luis",
                fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.padding(horizontal = 18.dp))
        Box(
            modifier = Modifier
                .background(Color(0xFFE4E4E4), shape = RoundedCornerShape(16.dp))
        ) {
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            //Circulo pequeñito de color negro
            Box(modifier = Modifier
                .size(12.dp)
                .background(Color.Black, shape = CircleShape)
                .align(Alignment.Center))
        
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(
                text = "No asistió",
                fontSize = 12.sp,
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
