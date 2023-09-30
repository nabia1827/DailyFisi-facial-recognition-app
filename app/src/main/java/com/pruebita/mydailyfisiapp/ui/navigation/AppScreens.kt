package com.pruebita.mydailyfisiapp.ui.navigation

sealed class AppScreens(val route: String){
    object SplashScreen: AppScreens("splash_screen")
    object StartScreen: AppScreens("start_screen")
    object LoginScreen: AppScreens("login_screen")
    object MainScreen: AppScreens("main_screen")

}
