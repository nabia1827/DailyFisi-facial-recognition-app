package com.pruebita.mydailyfisiapp.ui.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import com.pruebita.mydailyfisiapp.data.repository.repositories.GoogleAuthUiClient
import com.pruebita.mydailyfisiapp.ui.navigation.AppNavigation

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SuperScreen(
    navController: NavHostController,
    lifecycleScope: LifecycleCoroutineScope,
    googleAuthUiClient: GoogleAuthUiClient,
    applicationContext: Context
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        AppNavigation(navController = navController, start = "login",lifecycleScope, googleAuthUiClient,applicationContext)
    }
}