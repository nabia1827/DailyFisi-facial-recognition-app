package com.pruebita.mydailyfisiapp.ui.screens.attendance

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.res.painterResource
import com.pruebita.mydailyfisiapp.R
import okhttp3.internal.wait

@Preview(showBackground = true)
@Composable
fun  TodayPreview(){
    val navController = rememberNavController()
    TodayScreen(navController)
}
@Composable
fun  TodayScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TodaySS(Modifier.padding(0.dp),navController)
    }
}
@Composable
fun TodaySS(modifier: Modifier,navController: NavController){

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.padding(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(color = Color.White)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = CenterVertically
            ){
                Box(modifier = Modifier.padding(1.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_vector_array),
                        contentDescription = "ico_vector",
                        modifier = Modifier.size(16.dp, 16.dp)
                    )
                }
                Spacer(modifier =Modifier.width(12.dp))
                Column {
                    Text(
                        text = "06",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier =Modifier.width(10.dp))

                Column {
                    Row {
                        Text(
                            text = "Miercoles",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Row {
                        Text(
                            text = "Septiembre 2023",
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier =Modifier.width(60.dp))

                Surface(
                    modifier = modifier
                        .width(73.dp)
                        .height(40.dp),
                    color = Color(0xFFC8DBF8), // Color de fondo azul pastel
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Hoy",
                            color = Color(0xFF1D192B), // Color de texto gris
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

            }
            Row {
                Column(modifier = Modifier.padding(16.dp),
                    //Dentro de esto? o fuera ?
                    horizontalAlignment = Alignment.CenterHorizontally ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                    lineLogic(porcentaje = 0.4f)
                }
                Column(modifier = Modifier
                    .background(color = Color.White)
                    .padding(16.dp))
                {
                    CartillaCard("Calculo II:","Parte Teorica","12:00 - 13:30","Marcado",1)
                    CartillaCard("Calculo II:","Parte Practica","13:30 - 14:40","Marcado",1)
                    CartillaCard("Algoritmica I :","Parte Teórica ","15:00 - 16:00","Marcar",2)
                    CartillaCard("Algoritmica I :","Parte Practica ","15:00 - 16:00","Marcar",3)
                    CartillaCard("Marketing:","Parte Teorica","17:00 - 18:00","Marcar",3)
                    CartillaCard("Marketing:","Parte Practica ","15:00 - 16:00","Marcar",3)
                }
            }

    }
}
}
@Composable
fun lineLogic(porcentaje: Float){
    Box {

        Canvas(
            modifier = Modifier
                .height(570.dp)
                .width(6.dp)
                .padding(8.dp)
        ){
            val lineHeight = size.height


            // Dibuja la línea vertical
            drawLine(
                color = Color(0xFF8B97A8),
                start = Offset(size.width / 2, 0f),
                end = Offset(size.width / 2, lineHeight),
                strokeWidth = 6f
            )
            // Calcula la posición vertical del círculo intermedio según el porcentaje
            val circleY = lineHeight * porcentaje

            // Dibuja la línea vertical GRUES
            drawLine(
                color = Color(0xFF2F89C3),
                start = Offset(size.width / 2, 0f),
                end = Offset(size.width / 2, circleY),
                strokeWidth = 16f
            )
            // Dibuja el círculo inicial
            drawCircle(
                color = Color(0xFF2F89C3),
                center = Offset(size.width / 2, 0f),
                radius = 25f
            )

            // Dibuja el círculo intermedio en la posición calculada
            drawCircle(
                color = Color(0xFF29D697), // Puedes personalizar el color
                center = Offset(size.width / 2, circleY),
                radius = 25f
            )

            // Dibuja el círculo final
            drawCircle(
                color = Color(0xFF8B97A8),
                center = Offset(size.width / 2, lineHeight),
                radius = 25f
            )
        }


    }
}

@Composable
fun CartillaCard(curso:String,prOtr:String, hora: String,isEvent: String,colorType: Int){
    Spacer(modifier = Modifier.height(14.dp))
    Box(
        modifier = Modifier
            .width(274.dp)
            .height(83.dp)
            .background(Color.White)
            .shadow(
                elevation = 2.dp, // Elevación de la sombra
                shape = MaterialTheme.shapes.medium
            )
    ){
        val backgroundColor = when (colorType) {
            1 -> Color(0xFF6C5FDA) // Azul pastel
            2 -> Color(0xFF29D697) // Verde (puedes cambiar el código de color)
            else -> Color.Gray // Color por defecto en caso de un valor no válido
        }
        Column(
            modifier = Modifier
                .fillMaxHeight() // Ocupa todo el espacio vertical disponible
                .width(10.dp) // Ancho de la columna delgada
                .background(backgroundColor) // Color de fondo morado
        ) {
            // Contenido de la columna
        }
        Spacer(modifier =  Modifier.width(34.dp))
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp, 10.dp, 15.dp, 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Spacer(modifier = Modifier.width(28.dp))
                Text(
                    text = curso,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = prOtr,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier =Modifier.height(4.dp) )
            Row (verticalAlignment = Alignment.CenterVertically){
                Spacer(modifier =Modifier.width(28.dp) )

                Box(modifier = Modifier.padding(1.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_reloj),
                        contentDescription = "ico_reloj",
                        modifier = Modifier.size(12.dp, 12.dp)
                    )
                }
                Column(Modifier.padding(3.dp)) {
                    Text(
                        text = hora,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.width(35.dp))
                Column {
                    ButtonCustom(isEvent,colorType)
                }
            }
        }

    }
}
@Composable
fun ButtonCustom(isEvent: String, colorType: Int) {
    val backgroundColor = when (colorType) {
        1 -> Color(0xFF6C5FDA) // Azul pastel
        2 -> Color(0xFF29D697) // Verde (puedes cambiar el código de color)
        else -> Color.Gray // Color por defecto en caso de un valor no válido
    }

    Surface(
        modifier = Modifier
            .width(105.dp)
            .height(40.dp),
        color = backgroundColor, // Color de fondo
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Verifica si colorType es igual a 1 y muestra el icono si es así
            if (colorType == 1) {
                Row(
                    modifier = Modifier.padding(1.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ico_calendar_v2),
                        contentDescription = "ico_vector",
                        modifier = Modifier.size(10.dp, 12.dp)
                    )
                    Text(
                        text = isEvent,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            } else {
                // Si colorType no es igual a 1, solo muestra el texto sin el icono
                Text(
                    text = isEvent,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
