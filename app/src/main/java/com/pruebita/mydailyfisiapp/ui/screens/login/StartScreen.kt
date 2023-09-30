package com.pruebita.mydailyfisiapp.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.components.login.HeaderStart
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import com.pruebita.mydailyfisiapp.ui.theme.poppins


@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        HeaderStart()
        Spacer(modifier = Modifier.padding(30.dp))
        LoginButton(navController)
        Spacer(modifier = Modifier.padding(10.dp))
        WithGoogleButton()
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "¿Necesitas ayuda?", fontSize = 16.sp, fontFamily = poppins)


    }
}

@Composable
fun LoginButton(navController: NavController) {
    ElevatedButton(
        onClick = {navController.navigate(AppScreens.LoginScreen.route) },
        modifier = Modifier
            .width(300.dp)
            .height(48.dp)
            .padding(0.dp),
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
                        colors = listOf(Color(0xFF6663D7), Color(0xFF1E92BA))
                    ),
                    shape = RoundedCornerShape(22.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Empezar", fontSize = 16.sp, fontFamily = poppins)
        }
    }
}

@Composable
fun WithGoogleButton() {
    ElevatedButton(
        onClick = { },
        modifier = Modifier
            .width(300.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color(0xFF4B75CC),
            disabledContainerColor = Color(0xFFB3B6C4)
        ),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 3.dp,
        ),
        enabled = true
    ) {

        Row() {
            Image(
                painter = painterResource(id = R.drawable.iconinicio),
                contentDescription = "Log In Google",
                modifier = Modifier.size(24.dp, 24.dp)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "Iniciar con Google", fontSize = 16.sp, fontFamily = poppins)

        }

    }

}

@Composable
fun MyDialog(){

}