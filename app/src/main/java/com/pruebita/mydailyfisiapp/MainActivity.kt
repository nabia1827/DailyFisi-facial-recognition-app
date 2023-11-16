package com.pruebita.mydailyfisiapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.repository.repositories.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.*
import com.pruebita.mydailyfisiapp.ui.screens.SuperScreen
import com.pruebita.mydailyfisiapp.ui.theme.MyDailyFisiAppTheme
import com.pruebita.mydailyfisiapp.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val loginViewModel: LoginViewModel by viewModels()
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDailyFisiAppTheme {
                // A surface container using the 'background' color from the theme

                //gol
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var currentUser = User()
                    SuperScreen(navController = navController,lifecycleScope, googleAuthUiClient, applicationContext)
                    //Prueba()
                    //ScheduleScreen()
                    //ZoomableImageWithButtonsAndTouch()
                    //HorarioScreen()
                    //DrawImageWithTextOnZoomableImage()
                    //AddReminderScreen()
                    //RecognizingScreen(navController)
                    //MainScreen(navController2 = navController)
                }
            }
        }
    }
}

