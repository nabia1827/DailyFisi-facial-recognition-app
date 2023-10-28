package com.pruebita.mydailyfisiapp.ui.screens.events.other

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Composable
fun DetailsEventNormalScreen(navController: NavHostController) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/imageneseventos/101.jpg"))
    }

    var img1 by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/fotosperfil/persona_prueba1.png"))
    }
    var img2 by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/fotosperfil/persona_prueba2.png"))
    }
    var img3 by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/fotosperfil/persona_prueba3.png"))
    }
    var org_img by remember {
        mutableStateOf<Uri?>(Uri.parse("https://dfapruebaf.blob.core.windows.net/fotosperfil/persona_prueba4.png"))
    }

    val uris = remember { mutableStateListOf(img1, img2, img3) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                )
                {
                    HeaderBackDetailsEvent(navController,selectedImageUri)
                    HeaderFloating(uris)
                }
            }
        }

        item {
            DetailsEventContent(org_img)

        }

    }
}
@OptIn(ExperimentalTextApi::class)
@Composable
fun DetailsEventContent(org_img: Uri?) {
    val brush = Brush.horizontalGradient(
        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        Text(
            text = "Open Day \nInterbank",
            style = TextStyle(
                fontSize = 35.sp,
                fontFamily = poppins,
                fontWeight = FontWeight(500),
                brush = brush

            )
        )
        DetailsCard(
            id = R.drawable.calendar_details,
            title = "10 Junio, 2023",
            subtitle = "Viernes, 2:00PM - 5:00PM"
        )
        DetailsCard(
            id = R.drawable.location_details,
            title = "Edificio Interbank",
            subtitle = "Carlos Villarán 140, La Victoria"
        )
        DetailsOrganizador(
            img = org_img,
            title = "Pedro Alvarez",
            subtitle = "Organizador"

        )
        DetailsDescription()

    }
}

@Composable
fun DetailsDescription() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 15.dp, bottom = 15.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Descripcion del evento",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                )
            )
        }
        Text(
            text = "Material Design is an adaptable system of guidelines, components, and tools that support the best practices of user interface design. Backed by open-source code, Material Design streamlines collaboration between designers and developers, and helps teams quickly build beautiful products.",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF56575A),
                textAlign = TextAlign.Justify,


                )
        )
    }
}

@Composable
fun DetailsCard(id:Int, title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(0.25f)
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .width(50.dp)
                    .background(
                        color = Color(0xFFEAEDFF),
                        shape = RoundedCornerShape(size = 12.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = id),
                    contentDescription = "my posts",
                    modifier = Modifier.padding(15.dp)

                )

            }
        }
        Column(
            modifier = Modifier.weight(0.75f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 34.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF120D26),

                    )
            )
            Text(
                text = subtitle,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF744747)

                )
            )

        }
    }
}

@Composable
fun DetailsOrganizador(img:Uri?, title: String, subtitle: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(0.25f)
        ) {
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .width(50.dp)
                    .height(50.dp)
                    .background(
                        color = Color(0xFFEAEDFF),
                        shape = RoundedCornerShape(size = 12.dp)
                    )
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(img)
                        .build(),
                    contentDescription = "This is an example image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop


                )

            }
        }
        Column(
            modifier = Modifier.weight(0.75f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 34.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF120D26),

                    )
            )
            Text(
                text = subtitle,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF744747)

                )
            )

        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun HeaderFloating(uris: MutableList<Uri?>) {
    val brush = Brush.verticalGradient(
        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
    )
    Row(
        modifier = Modifier
            .width(350.dp)
            .height(85.dp)
    ) {
        ElevatedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxSize()
                .padding(7.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFEFEFF),
                contentColor = Color(0xFF4A4A4A),
                disabledContainerColor = Color(0xFFB3B6C4)

            ),
            contentPadding = PaddingValues(),
            enabled = true,
            shape = RoundedCornerShape(28.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )

        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.weight(0.3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(){
                        com.pruebita.mydailyfisiapp.ui.screens.events.dele.GoerImage(uris[0], 0.dp)
                        com.pruebita.mydailyfisiapp.ui.screens.events.dele.GoerImage(uris[1], 17.dp)
                        com.pruebita.mydailyfisiapp.ui.screens.events.dele.GoerImage(uris[2], 34.dp)
                    }
                }

                Column(
                    modifier = Modifier.weight(0.35f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "+20  asistirán",
                        style = TextStyle(
                            brush = brush,
                            fontSize = 16.sp,
                            lineHeight = 26.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight.Medium,
                        )
                    )
                }
                Column(
                    modifier = Modifier.weight(0.35f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
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
                            disabledContainerColor = Color(0xFFB3B6C4)

                        ), contentPadding = PaddingValues(),
                        enabled = true
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            Color(0xFF6663D7),
                                            Color(0xFF1E92BA)
                                        )
                                    ),
                                    shape = RoundedCornerShape(22.dp)
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(text = "Agendar", fontSize = 12.sp, fontFamily = poppins)
                        }
                    }
                }

            }
        }

    }
}

@Composable
fun HeaderBackDetailsEvent(navController:NavHostController,selectedImageUri: Uri?) {
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.9f)
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(selectedImageUri)
                        .build(),
                    contentDescription = "This is an example image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(0.dp)),
                    contentScale = ContentScale.Crop


                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
            ) {

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },

                ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back",
                    tint = Color.White,
                )

            }
            Text(
                text = "Eventos",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFFFFFFFF),

                    )
            )
        }

    }
}