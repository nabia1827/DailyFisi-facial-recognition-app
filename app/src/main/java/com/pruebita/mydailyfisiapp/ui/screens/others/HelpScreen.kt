package com.pruebita.mydailyfisiapp.ui.screens.others

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins
import kotlin.math.absoluteValue

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable

fun HelpScreen(){

    val pagerState = rememberPagerState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            RotateImage(pagerState)
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),

            ){
                InfoCard(pagerState)
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfoCard(pagerState: PagerState) {
    val titulo = listOf(
        "Visualice todo a través de su Pagina de Inicio",
        "Supervisa la Asistencia en un Vistazo en DailyFisi",
        "Identifica la Ubicación y el Alcance de tus cursos",
        "Mantente Informado de las actividades con DailyFisi"
    )

    val contenido = listOf(
        "Te invitamos a explorar minuciosamente todas las funciones y características que nuestra plataforma ofrece a través de su menú ",
        "Llevar un seguimiento preciso del registro de asistencias en tu salón de clases nunca ha sido tan sencillo y eficiente",
        "Te ofrecemos la conveniencia de acceder a toda la información relevante en un solo lugar, facilitando tu jornada académica",
        " Te proporcionamos un acceso directo a información actualizada sobre conferencias, actividades y anuncios importantes"
    )



    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.base_linea_ayuda),
            contentDescription = "Info Card",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.FillBounds
        )
        Column (
            modifier =  Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(Modifier.weight(0.1f))
            Text(
                text = titulo[pagerState.currentPage],
                modifier = Modifier
                    .weight(0.2f)
                    .padding(start = 45.dp, end = 45.dp),
                textAlign = TextAlign.Center,
                fontFamily = poppins,
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 40.sp
            )
            Spacer(Modifier.weight(0.07f))
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f)
                    .padding(start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = contenido[pagerState.currentPage],
                    textAlign = TextAlign.Center,
                    fontFamily = poppins,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 24.sp
                )

            }

            Spacer(Modifier.weight(0.05f))
            Row(
                modifier = Modifier.weight(0.1f)
            ){
                repeat(4) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.White else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(11.dp)
                    )
                }
            }
            Spacer(Modifier.weight(0.05f))
        }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RotateImage(pagerState: PagerState) {
    var pantalla = listOf(
        R.drawable.pantalla_home_1,
        R.drawable.pantalla_asistencias,
        R.drawable.pantalla_horarios,
        R.drawable.pantalla_eventos
    )

    HorizontalPager(
        state = pagerState,
        pageCount = 4,
        pageSpacing = -0.dp,
        reverseLayout = false,
        verticalAlignment = Alignment.Bottom
    ) { page ->
        Card(
            Modifier
                .fillMaxHeight()
                .width(420.dp)
,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(

            ){
                Image(
                    painter = painterResource(id = pantalla[page]),
                    contentDescription ="valor",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}