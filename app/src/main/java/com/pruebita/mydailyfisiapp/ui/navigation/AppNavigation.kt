package com.pruebita.mydailyfisiapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.pruebita.mydailyfisiapp.ui.screens.login.*
import com.pruebita.mydailyfisiapp.ui.screens.home.*
import com.pruebita.mydailyfisiapp.ui.screens.attendance.*
import com.pruebita.mydailyfisiapp.ui.screens.schedule.*
import com.pruebita.mydailyfisiapp.ui.screens.events.*
import com.pruebita.mydailyfisiapp.ui.screens.others.HelpScreen
import com.pruebita.mydailyfisiapp.ui.screens.others.SettingsScreen
import com.pruebita.mydailyfisiapp.ui.screens.others.SplashScreen
import com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer.*
@Composable
fun AppNavigation(navController: NavHostController, start:String) {
    NavHost(
        navController = navController,
        startDestination = start
    ) {
        navigation(startDestination = AppScreens.SplashScreen.route, route = "login"){
            composable(route = AppScreens.SplashScreen.route) {
                SplashScreen(navController)
            }
            composable(route = AppScreens.StartScreen.route) {
                StartScreen(navController)
            }
            composable(route = AppScreens.LoginScreen.route) {
                LoginScreen(navController)
            }

            composable(route = AppScreens.MainScreen.route){
                MainScreen(navController)
            }

            composable(route = AppScreens.FaceRecognizerScreen.route + "/{error}",
                arguments = listOf(navArgument(name = "error"){
                    type = NavType.BoolType
                })
            )
            {
                val errorParam = it.arguments?.getBoolean("error") ?: false
                FaceRecognizerScreen(navController,errorParam)
            }

            composable(route = AppScreens.RecognizingScreen.route){
                RecognizingScreen(navController)
            }

        }
        navigation(startDestination = ItemMenu.HomeScreen.route, route = "main") {
            composable(route = ItemMenu.HomeScreen.route){
                HomeScreen()
            }

            composable(route = ItemMenu.AttendanceScreen.route){
                AttendanceScreen(navController)
            }

            composable(route = ItemMenu.ScheduleScreen.route){
                ScheduleScreen()
            }

            composable(route = ItemMenu.EventsScreen.route){
                EventsScreen()
            }
            composable(route = DrawerItem.SettingsScreen.route){
                SettingsScreen()
            }
            composable(route = DrawerItem.HelpScreen.route){
                HelpScreen()
            }
            // Extra
            composable(route = InternalScreens.TodayScreen.route){
                TodayScreen(navController)
            }

            composable(route = InternalScreens.AttendanceReportScreen.route){
                AttendanceReportScreen()
            }

        }


    }


}