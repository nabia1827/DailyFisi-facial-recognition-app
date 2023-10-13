package com.pruebita.mydailyfisiapp.ui.screens.events

import android.graphics.fonts.Font
import android.graphics.fonts.FontFamily
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
import com.pruebita.mydailyfisiapp.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.screens.home.HeaderHome
import com.pruebita.mydailyfisiapp.ui.theme.poppins

@Preview(showBackground = true)
@Composable
fun EventsScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {
                        HeaderEventsScreen()
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    ) {
                        Text(text = "       ")
                    }


                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(start = 10.dp),
                ) {
                    item {
                        FilterCard(Color(0xFFF25E56), R.drawable.pelota_ic, "Deportes")
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                    item {
                        FilterCard(Color(0xFFFA9032), R.drawable.book_ic, "Estudios")
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                    item {
                        FilterCard(Color(0xFF29D697), R.drawable.medal_ic, "Logros")
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                }

            }
        }
        item {
            Spacer(modifier = Modifier.padding(9.dp))
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    SelectorPosts()
                }
                Column() {
                    UpcomingEvents()
                }
                Column() {
                    TrendingNews()
                }

            }
        }
    }
}

@Composable
fun SelectorPosts() {
    Row(
        modifier = Modifier.fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
                .padding(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ElevatedButton(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxSize(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color(0xFFFFFFFF),
                    disabledContainerColor = Color(0xFFB3B6C4)

                ), contentPadding = PaddingValues(), //Es soluuu no tocar
                enabled = true,
                shape = RoundedCornerShape(14.dp)

            ) {
                Column(
                    modifier = Modifier.background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                        ),

                    ).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Tendencias", fontSize = 16.sp, fontFamily = poppins)
                }

            }


        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Hika")

        }


    }
}

@Composable
fun TrendingNews() {

}

@Composable
fun UpcomingEvents() {

}


@Composable
fun FilterCard(color: Color, id: Int, text: String) {
    Row(
        modifier = Modifier
            .width(140.dp)
            .height(55.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(size = 20.96263.dp)
            )
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Icon(
            painter = painterResource(id = id),
            contentDescription = "ball",
            tint = Color.White,
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = text,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 25.sp,
                fontFamily = poppins,
                fontWeight = FontWeight(600),
                color = Color.White,
            )
        )


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderEventsScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                    ),
                ),
        ) {

        }
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "FISI - UNMSM",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),

                    textAlign = TextAlign.Center,
                )
            )

            Text(
                text = "Lima, Peru",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF4F4FE),

                    textAlign = TextAlign.Center,
                )
            )
            Spacer(modifier = Modifier.padding(2.dp))

            OutlinedTextField(
                value = "",
                onValueChange = { },
                enabled = true,
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFF4F4FE),

                    textAlign = TextAlign.Start,
                ),
                placeholder = {
                    Text(
                        text = "Realizar busqueda",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = poppins,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFF4F4FE),

                            textAlign = TextAlign.Center,
                        )
                    )
                },
                leadingIcon =
                {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "User",
                        tint = Color.White
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(size = 50.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0x8FFFFFFF),
                    unfocusedBorderColor = Color(0x8FFFFFFF),
                    focusedBorderColor = Color(0x8FFFFFFF),
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,

                    ),

                )

        }


    }
}