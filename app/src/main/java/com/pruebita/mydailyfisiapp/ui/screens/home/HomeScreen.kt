package com.pruebita.mydailyfisiapp.ui.screens.home

import android.widget.Space
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.screens.attendance.AttendanceScreen
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import kotlin.math.ceil
import kotlin.math.floor

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item{
            Spacer(modifier = Modifier.padding(2.dp))
            HeaderHome()
        }
        item {
            Spacer(modifier = Modifier.padding(8.dp))
            CardEvent()
        }
        item{
            Spacer(modifier = Modifier.padding(8.dp))
            ClasesSection()
        }
        item{
            Spacer(modifier = Modifier.padding(8.dp))
            AttendanceSection()

        }


    }
}

@Composable
fun AttendanceSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ){
        AttendanceSectionTitle()
        AttendanceSectionContent(4)

    }
}

@Composable
fun AttendanceSectionContent(cantidad: Int) {
    val rows = floor(cantidad/2.0).toInt()
    for (i in 0 until rows) {
        AttendanceSectionCard(2)
        Spacer(modifier = Modifier.padding(4.dp))
    }
    if(cantidad%2!=0){
        AttendanceSectionCard(1)
    }
}

@Composable
fun AttendanceSectionCard(num:Int) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                    ),
                    shape = RoundedCornerShape(22.dp)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Algoritmica I",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFFFFFF),
                )
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        if(num ==2){
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                        ),
                        shape = RoundedCornerShape(22.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Algoritmica I",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 22.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFFFFFFF),
                    )
                )
            }

        }else{
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFFFFF))
                        ),
                        shape = RoundedCornerShape(22.dp)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }

    }

}

@Composable
fun AttendanceSectionTitle() {
    Row(){
        Text(
            text = "Mis Asistencias ",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        )

    }
}

@Composable
fun ClasesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ){
        ClasesSectionTitle()
        ClasesSectionInstantClass()
        Spacer(modifier = Modifier.padding(7.dp))
        ClasesSectionLaterClass()
    }
}

@Composable
fun ClasesSectionLaterClass() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)


    ){
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
                .background(
                    color = Color(0xFFF8F8F8),
                    shape = RoundedCornerShape(size = 10.dp)
                ).padding(start=12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LaterClassContent()
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
                .background(
                    color = Color(0xFFF8F8F8),
                    shape = RoundedCornerShape(size = 10.dp)
                ).padding(start=10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LaterClassContent()
        }

    }
}

@Composable
fun ClasesSectionInstantClass() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(122.dp)
            .background(
                color = Color(0xFFF8F8F8),
                shape = RoundedCornerShape(size = 10.dp)
            )

    ){
        Column(
            modifier = Modifier
                .weight(0.55f)
                .fillMaxHeight().padding(start =15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InstantClassIzq()
        }
        Column(
            modifier = Modifier
                .weight(0.45f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InstantClassDer()
        }

    }
}

@Composable
fun InstantClassDer() {
    Text(
        text = "5 minutos para iniciar",
        style = TextStyle(
            fontSize = 11.sp,
            lineHeight = 15.sp,
            fontFamily = poppins,
            fontWeight = FontWeight(500),
            color = Color(0xFF000000),
        )
    )
    Text(
        text = "11:55:56",
        style = TextStyle(
            fontSize = 24.sp,
            lineHeight = 36.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF44336),
        )
    )
}
@Composable
fun LaterClassContent() {
    Text(
        text = "Calculo II ",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
            textAlign = TextAlign.Start
        )
    )
    Row(
        modifier = Modifier.height(30.dp)
    ){
        Column (
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        color = Color(0xFFE8EBFF),
                        shape = RoundedCornerShape(size = 103.dp)
                    )
                    .padding(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
            ){
                Text(
                    text = " Aula 102 ",
                    style = TextStyle(
                        fontSize = 13.sp,
                        lineHeight = 14.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF495ECA),
                    )
                )
            }

        }
        Spacer(modifier = Modifier.padding(4.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.4f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "clock",
                    tint = Color(0xFF495ECA),

                    )
                Text(
                    text = "12:00AM",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF495ECA),
                    ),


                    )
            }

        }
    }
}





@Composable
fun InstantClassIzq() {
    Text(
        text = "Calculo II ",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 24.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
            textAlign = TextAlign.Start
        )
    )
    Row(
        modifier = Modifier.height(30.dp)
    ){
        Column (
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        color = Color(0xFFE8EBFF),
                        shape = RoundedCornerShape(size = 103.dp)
                    )
                    .padding(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 4.dp)
            ){
                Text(
                    text = " Aula 102 ",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 14.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF495ECA),
                    )
                )
            }

        }
        Spacer(modifier = Modifier.padding(4.dp))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "clock",
                    tint = Color(0xFFF44336),

                )
                Text(
                    text = "12:00AM",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFF44336),
                    ),


                )
            }

        }
    }
}

@Composable
fun ClasesSectionTitle() {
    Row(){
        Text(
            text = "4 Clases Pendientes ",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        )
        Icon(
            painter = painterResource(id = R.drawable.warning),
            contentDescription = "class",
            tint = Color(0xFFF44336)
        )

    }
}

@Composable
fun CardEvent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                color = Color(0xFF495ECA),
                shape = RoundedCornerShape(size = 20.dp)
            )

    ) {
        Box(){
            Image(
                painter = painterResource(id = R.drawable.fondo_evento_home),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            ContentCardEvent()
        }

    }
}

@Composable
fun ContentCardEvent() {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier.weight(0.7f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hackathon",
                    style = TextStyle(
                        fontSize = 28.sp,
                        lineHeight = 34.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 0.8.sp,
                    )
                )
                Text(
                    text = "Vol 2",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                    )
                )
            }
            Row(){
                Text(
                    text = "El día de hoy se presentaran 5 grupos en el Auditorio.",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFFFFFFFF),
                    )
                )
            }
        }
        Column(
            modifier = Modifier.weight(0.3f)
        ) {
            ElevatedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF495EC9),
                    disabledContainerColor = Color(0xFFB3B6C4)

                ), contentPadding = PaddingValues(),
                enabled = true
            ) {
                Text(text = "Ver mas", fontSize = 13.sp, fontFamily = poppins)
            }

        }
    }
}

@Composable
fun HeaderHome() {
    Text(
        text = "¡Hola, Miguel!",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 28.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            letterSpacing = 0.78.sp,
        )

    )
    Text(
        text = "Hay un evento en la facultad esta mañana ",
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            letterSpacing = 0.48.sp,
        )
    )
}


