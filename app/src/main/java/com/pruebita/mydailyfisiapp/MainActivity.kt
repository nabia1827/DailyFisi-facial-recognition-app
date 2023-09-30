package com.pruebita.mydailyfisiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.pruebita.mydailyfisiapp.ui.navigation.AppNavigation
import com.pruebita.mydailyfisiapp.ui.screens.SuperScreen
import com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer.FaceRecognizerScreen
import com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer.RecognizingScreen
import com.pruebita.mydailyfisiapp.ui.screens.home.MainScreen
import com.pruebita.mydailyfisiapp.ui.screens.login.LoginScreen
import com.pruebita.mydailyfisiapp.ui.screens.login.StartScreen
import com.pruebita.mydailyfisiapp.ui.theme.MyDailyFisiAppTheme

class MainActivity : ComponentActivity() {
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
                    //SuperScreen(navController = navController)
                    FaceRecognizerScreen()
                    //MainScreen(navController2 = navController)
                }
            }
        }
    }
}

