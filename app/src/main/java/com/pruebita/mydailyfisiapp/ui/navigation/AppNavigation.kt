package com.pruebita.mydailyfisiapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.pruebita.mydailyfisiapp.ui.screens.attendance.student.*
import com.pruebita.mydailyfisiapp.ui.screens.attendance.teacher.*
import com.pruebita.mydailyfisiapp.ui.screens.schedule.*
import com.pruebita.mydailyfisiapp.ui.screens.events.*
import com.pruebita.mydailyfisiapp.ui.screens.events.other.*
import com.pruebita.mydailyfisiapp.ui.screens.events.dele.AddEventScreen
import com.pruebita.mydailyfisiapp.ui.screens.events.dele.AddNewScreen
import com.pruebita.mydailyfisiapp.ui.screens.events.dele.DetailsEventScreen
import com.pruebita.mydailyfisiapp.ui.screens.events.dele.EditEventScreen
import com.pruebita.mydailyfisiapp.ui.screens.events.dele.EditNewScreen
import com.pruebita.mydailyfisiapp.ui.screens.events.dele.EventsScreen
import com.pruebita.mydailyfisiapp.ui.screens.others.HelpScreen
import com.pruebita.mydailyfisiapp.ui.screens.others.SettingsScreen
import com.pruebita.mydailyfisiapp.ui.screens.others.SplashScreen
import com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer.*
@RequiresApi(Build.VERSION_CODES.O)
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

            composable(route = AppScreens.MainStudentScreen.route){
                MainStudentScreen(navController)
            }
            composable(route = AppScreens.MainDeleScreen.route){
                MainDeleScreen(navController)
            }
            composable(route = AppScreens.MainTeacherScreen.route){
                MainTeacherScreen(navController)
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

        /*
            *  SUBMODULE OF HOME
            * */

        composable(route = ItemMenu.HomeScreen.routeStudent){
            HomeScreen()
        }
        composable(route = ItemMenu.HomeScreen.routeDele){
            HomeScreen()
        }
        composable(route = ItemMenu.HomeScreen.routeTeacher){
            HomeScreen()
        }
        /*
        *  SUBMODULE OF ATTENDANCE
        * */

        composable(route = ItemMenu.AttendanceScreen.routeStudent){
            AttendanceStudentScreen(navController)
        }
        composable(route = ItemMenu.AttendanceScreen.routeDele){
            AttendanceStudentScreen(navController)
        }
        composable(route = ItemMenu.AttendanceScreen.routeTeacher){
            AttendanceTeacherScreen(navController)
        }

        // Sub screens
        composable(route = InternalScreens.TodayAttendanceStudentScreen.route){
            TodayAttendanceStudentScreen(navController)
        }

        composable(route = InternalScreens.AttendanceReportStudentScreen.route){
            AttedanceReportStudentScreen(navController)
        }

        composable(route = InternalScreens.VerifyingIdentityStudentScreen.route){
            VerifyingIdentityStudentScreen(navController)
        }

        // Sub screens
        composable(route = InternalScreens.AttendanceListTeacherScreen.route){
            AttendanceListTeacherScreen(navController)
        }

        composable(route = InternalScreens.AttendanceReportTeacherScreen.route){
            AttedanceReportTeacherScreen(navController)
        }
        composable(route = InternalScreens.CurseReportTeacherScreen.route){
            CurseReportTeacherScreen(navController)
        }

        composable(route = InternalScreens.TodayAttendanceTeacherScreen.route){
            TodayAttendanceTeacherScreen(navController)
        }


        /*
        *  SUBMODULE OF SCHEDULE
        * */

        composable(route = ItemMenu.ScheduleScreen.routeStudent){
            ScheduleScreen(navController)
        }
        composable(route = ItemMenu.ScheduleScreen.routeDele){
            ScheduleScreen(navController)
        }
        composable(route = ItemMenu.ScheduleScreen.routeTeacher){
            ScheduleScreen(navController)
        }

        // Sub screens
        composable(route = InternalScreens.AddReminderScreen.route){
            AddReminderScreen(navController)
        }
        composable(route = InternalScreens.HorarioScreen.route){
            HorarioScreen(navController)
        }
        composable(route = InternalScreens.LocationScreen.route){
            LocationScreen(navController)
        }

        /*
        *  SUBMODULE OF EVENTS
        * */
        composable(route = ItemMenu.EventsScreen.routeStudent){
            EventsNormalScreen(navController)
        }
        composable(route = ItemMenu.EventsScreen.routeDele){
            EventsScreen(navController)
        }
        //Sub screens
        composable(route = InternalScreens.AddEventScreen.route){
            AddEventScreen(navController)
        }

        composable(route = InternalScreens.AddNewScreen.route){
            AddNewScreen(navController)
        }

        composable(route = InternalScreens.DetailsEventScreen.route){
            DetailsEventScreen(navController)
        }
        composable(route = InternalScreens.EditEventScreen.route){
            EditEventScreen(navController)
        }
        composable(route = InternalScreens.EditNewScreen.route){
            EditNewScreen(navController)
        }

        composable(route = InternalScreens.DetailsEventNormalScreen.route){
            DetailsEventNormalScreen(navController)
        }
        composable(route = ItemMenu.EventsScreen.routeTeacher){
            EventsNormalScreen(navController)
        }

        /*
        *  SUBMODULE OF OTHERS
        * */
        composable(route = DrawerItem.SettingsScreen.routeStudent){
            SettingsScreen()
        }
        composable(route = DrawerItem.HelpScreen.routeStudent){
            HelpScreen()
        }




    }


}