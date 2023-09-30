package com.pruebita.mydailyfisiapp.ui.screens.others

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pruebita.mydailyfisiapp.R
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {
    LaunchedEffect(key1 = true){
        delay(5400)
        navController.popBackStack()
        navController.navigate(AppScreens.StartScreen.route)
    }
    Splash()
}

@Preview(showBackground = true)
@Composable
fun Splash(){
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.cubitobonito))
    val progress by animateLottieCompositionAsState(composition)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.fillMaxSize(),
            composition = composition, progress = {progress},
            contentScale = ContentScale.FillBounds
        )

    }
}