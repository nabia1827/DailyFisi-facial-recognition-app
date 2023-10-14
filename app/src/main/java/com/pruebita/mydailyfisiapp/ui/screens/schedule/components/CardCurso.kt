package com.pruebita.mydailyfisiapp.ui.screens.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pruebita.mydailyfisiapp.R

@Preview(showBackground = true)
@Composable
fun cardCurso(){
    var curso = "Calculo III"
    var seccion = "Seccion 3"
    var teoria = "Aula 102 NP"
    var labo = "Lab 05 NP"
    var docente = "Oswaldo Lopez Michellini"
    var isLabo = true
    var isactual = true
    val otros = Brush.horizontalGradient(
        colors = listOf(Color(0xFFC8DBF8), Color(0xFFC8DBF8))
    )
    val actual = Brush.verticalGradient(
        colors = listOf(Color(0xFF1D93BB), Color(0xFF4579CB), Color(0xFF6C5FDA))
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = actual)
            .padding(15.dp)

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Box(
                modifier = Modifier
                    .weight(0.9f)
            ){
                Text(
                    text = curso,
                    color = Color.White
                )
            }

            Box(
                modifier = Modifier
                    .weight(0.1f),
                contentAlignment = Alignment.CenterEnd

            ){
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null, // Puedes proporcionar una descripción opcional
                    tint = Color.White, // Color del icono
                    modifier = Modifier.wrapContentSize() // Tamaño del icono
                )
            }

        }
        Spacer(Modifier.padding(5.dp))

        Text(
            text = seccion,
            color = Color.White
        )
        Spacer(Modifier.padding(5.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null, // Puedes proporcionar una descripción opcional
                tint = Color.White, // Color del icono
                modifier = Modifier.wrapContentSize() // Tamaño del icono
            )
            Spacer(Modifier.padding(5.dp))
            Text(
                text ="T: $teoria",
                color = Color.White,
                modifier = Modifier.wrapContentHeight()
            )
            Spacer(Modifier.padding(5.dp))
            Text(
                text ="L: $labo",
                color = Color.White
            )
        }
        Spacer(Modifier.padding(5.dp))
        Row {
            Icon(
                painter = painterResource(id = R.drawable.baseline_person_outline_24),
                contentDescription = null, // Puedes proporcionar una descripción opcional
                tint = Color.White, // Color del icono
                modifier = Modifier.size(8.dp) // Tamaño del icono
            )
            Spacer(Modifier.padding(5.dp))
            Text(
                text ="Prof. $docente",
                color = Color.White
            )
        }

    }

}