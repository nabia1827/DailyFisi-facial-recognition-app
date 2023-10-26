package com.pruebita.mydailyfisiapp.ui.components.login

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.theme.poppins


@Composable
fun HeaderMenu(selectedImageUri: Uri?){
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.polygon_secundary_menu),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )

        Image(
            painter = painterResource(id = R.drawable.polygonfront),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start

        ) {
            Spacer(modifier = Modifier.padding(12.dp))
            Box(contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(selectedImageUri)
                        .transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = "This is an example image",
                    modifier = Modifier.clip(CircleShape)
                        .size(62.0.dp)
                        .border(width = 1.33333.dp, color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 62.dp))

                )
            }
            Spacer(modifier = Modifier.padding(7.dp))

            Column(
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Nabia Pachas Lopez",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppins,
                    color = Color.White
                )
                Text(
                    text = "nabia.pachas@unmsm.edu.pe",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = poppins,
                    color = Color.White
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Box(
                    Modifier
                        .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 20.dp))
                        .padding(start = 12.dp, top = 5.dp, end = 12.dp, bottom = 5.dp)

                ){
                    Text(
                        text = "Estudiante",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = poppins,
                        color = Color(0xFF4B75CC)
                    )
                }
            }
        }
    }
}