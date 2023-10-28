package com.pruebita.mydailyfisiapp.ui.screens.attendance.teacher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.ui.screens.events.dele.EventsScreen
import com.pruebita.mydailyfisiapp.ui.theme.poppins
@Preview(showBackground = true)
@Composable
fun PreviewAttendanceListTeacherScreen(){
    val navController = rememberNavController()
    AttendanceListTeacherScreen(navController)
}


@Composable
fun AttendanceListTeacherScreen(navController: NavHostController) {

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
                HeaderListScreen(navController)
            }
        }
        item {
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

        item{
            StudentItem()
            StudentItem()
            StudentItem()
            StudentItem()
            StudentItem()
            StudentItem()
            StudentItem()
            StudentItem()

        }


    }
}

@Composable
fun StudentItem() {
    val brush = remember {
        Brush.horizontalGradient(
            colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
        )
    }
    ListItem(
        headlineContent = {
            Text(
                text = "Nuñez Zegarra,",
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
                text = "Oscar Luis",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF959595),

                    )
            )

        },
        trailingContent = {
            AttendanceChip()
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .background(brush = brush, shape = CircleShape)
                    .size(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "ON",
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
fun AttendanceChip() {
    var selected by rememberSaveable { mutableStateOf(false) }

    FilterChip(
        onClick = { selected = !selected },
        label = {
            if (selected)
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
        selected = selected,
        leadingIcon =
        if (!selected) {
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
fun HeaderListScreen(navController: NavHostController) {
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
                text = "Algoritmica",
                style = TextStyle(
                    brush = brush,
                    fontSize = 34.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.SemiBold,

                )

            )
            Text(
                text = "Seccion 3 - Teoría",
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
