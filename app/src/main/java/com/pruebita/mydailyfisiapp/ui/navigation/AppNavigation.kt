package com.pruebita.mydailyfisiapp.ui.navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.pruebita.mydailyfisiapp.data.repository.repositories.GoogleAuthUiClient
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
import com.pruebita.mydailyfisiapp.viewmodel.AttendanceListTeacherViewModel
import com.pruebita.mydailyfisiapp.viewmodel.AttendanceReportStudentViewModel
import com.pruebita.mydailyfisiapp.viewmodel.AttendanceReportTeacherViewModel
import com.pruebita.mydailyfisiapp.viewmodel.ClockViewModel
import com.pruebita.mydailyfisiapp.viewmodel.CurseReportStudentViewModel
import com.pruebita.mydailyfisiapp.viewmodel.CurseReportTeacherViewModel
import com.pruebita.mydailyfisiapp.viewmodel.LoginViewModel
import com.pruebita.mydailyfisiapp.viewmodel.ScheduleViewModel
import com.pruebita.mydailyfisiapp.viewmodel.TodayAttendanceStudentViewModel
import com.pruebita.mydailyfisiapp.viewmodel.TodayAttendanceTeacherViewModel
import com.pruebita.mydailyfisiapp.viewmodel.VerifyingIdentityStudentViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    navController: NavHostController,
    start: String,
    lifecycleScope: LifecycleCoroutineScope?,
    googleAuthUiClient: GoogleAuthUiClient?,
    applicationContext: Context?
) {
    val clockViewModel: ClockViewModel = hiltViewModel()
    val todayAttendanceStudentViewModel: TodayAttendanceStudentViewModel = hiltViewModel()
    val attendanceReportStudentViewModel: AttendanceReportStudentViewModel = hiltViewModel()
    val curseReportStudentViewModel: CurseReportStudentViewModel = hiltViewModel()
    val attendanceListTeacherViewModel: AttendanceListTeacherViewModel = hiltViewModel()
    val curseReportTeacherViewModel: CurseReportTeacherViewModel = hiltViewModel()

    val scheduleViewModel: ScheduleViewModel = hiltViewModel()

    val verifyingIdentityStudentViewModel: VerifyingIdentityStudentViewModel = hiltViewModel()

    val todayAttendanceTeacherViewModel: TodayAttendanceTeacherViewModel = hiltViewModel()

    val attendanceReportTeacherViewModel: AttendanceReportTeacherViewModel = hiltViewModel()


    NavHost(
        navController = navController,
        startDestination = start
    ) {
        navigation(startDestination = AppScreens.SplashScreen.route, route = "login") {
            composable(route = AppScreens.SplashScreen.route) {
                SplashScreen(navController)
            }
            composable(route = AppScreens.StartScreen.route) {
                if(lifecycleScope != null && googleAuthUiClient != null && applicationContext !=null){
                    val loginViewModel: LoginViewModel = hiltViewModel()
                    val state by loginViewModel.state.collectAsStateWithLifecycle()
                    LaunchedEffect(key1 = Unit){
                        if(googleAuthUiClient.getSignedUser() !=null){
                            val userGoogle = googleAuthUiClient.getSignedUser()
                            if (userGoogle != null) {
                                loginViewModel.saveLocallyUserDataFromGoogle(userGoogle)
                                val actualRoute = loginViewModel.getMainRoute()
                                navController.navigate("$actualRoute/true")
                                loginViewModel.resetState()
                            }
                        }
                    }


                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    loginViewModel.onSignWithGoogleResult(signInResult)
                                }

                            }
                        }
                    )
                    LaunchedEffect(key1 = state.isSignInSuccessful){
                        if(state.isSignInSuccessful){
                            Toast.makeText(
                                applicationContext,
                                "yaaaa",
                                Toast.LENGTH_LONG
                            ).show()
                            val userGoogle = googleAuthUiClient.getSignedUser()
                            if (userGoogle != null) {
                                loginViewModel.saveLocallyUserDataFromGoogle(userGoogle)
                                val actualRoute = loginViewModel.getMainRoute()
                                navController.navigate("$actualRoute/true")
                                loginViewModel.resetState()
                            }

                        }
                    }

                    StartScreen(
                        navController = navController,
                        onSignInClick = {

                            lifecycleScope.launch {
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        }
                    )
                }
            }
            composable(route = AppScreens.LoginScreen.route) {
                if(lifecycleScope != null && googleAuthUiClient != null && applicationContext !=null){
                    val loginViewModel: LoginViewModel = hiltViewModel()
                    val state by loginViewModel.state.collectAsStateWithLifecycle()
                    LaunchedEffect(key1 = Unit){
                        if(googleAuthUiClient.getSignedUser() !=null){
                            val userGoogle = googleAuthUiClient.getSignedUser()
                            if (userGoogle != null) {
                                loginViewModel.saveLocallyUserDataFromGoogle(userGoogle)
                                val actualRoute = loginViewModel.getMainRoute()
                                navController.navigate("$actualRoute/true")
                                loginViewModel.resetState()
                            }
                        }
                    }


                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if (result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = googleAuthUiClient.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    loginViewModel.onSignWithGoogleResult(signInResult)
                                }

                            }
                        }
                    )
                    LaunchedEffect(key1 = state.isSignInSuccessful){
                        if(state.isSignInSuccessful){
                            Toast.makeText(
                                applicationContext,
                                "yaaaa",
                                Toast.LENGTH_LONG
                            ).show()
                            val userGoogle = googleAuthUiClient.getSignedUser()
                            if (userGoogle != null) {
                                loginViewModel.saveLocallyUserDataFromGoogle(userGoogle)
                                if(loginViewModel.isFirstLogin.value == true){
                                    navController.navigate(route = AppScreens.FaceRecognizerScreen.route + "/false"+"/true")
                                }else{
                                    val actualRoute = loginViewModel.getMainRoute()
                                    navController.navigate("$actualRoute/true")
                                    loginViewModel.resetState()
                                }

                            }

                        }
                    }

                    LoginScreen(
                        navController = navController,
                        state = state,
                        onSignInClick = {

                            lifecycleScope.launch {
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest.Builder(
                                        signInIntentSender ?: return@launch
                                    ).build()
                                )
                            }
                        },
                        loginViewModel
                    )
                }
            }

            composable(
                route = AppScreens.MainStudentScreen.route +"/{isGoogleAccount}",
                arguments = listOf(navArgument(name = "isGoogleAccount"){
                    type = NavType.BoolType
                })

            ) {
                val isGoogleAccount = it.arguments?.getBoolean("isGoogleAccount")?:false
                MainStudentScreen(
                    navController,
                    isGoogleAccount
                ) {
                    if (lifecycleScope != null) {
                        lifecycleScope.launch {
                            if (googleAuthUiClient != null) {
                                googleAuthUiClient.signOut()
                                Toast.makeText(
                                    applicationContext,
                                    "out",
                                    Toast.LENGTH_LONG
                                ).show()

                                //Navigate back
                            }
                        }
                    }
                }
            }

            composable(
                route = AppScreens.MainDeleScreen.route +"/{isGoogleAccount}",
                arguments = listOf(navArgument(name = "isGoogleAccount"){
                    type = NavType.BoolType
                })

            ) {
                val isGoogleAccount = it.arguments?.getBoolean("isGoogleAccount")?:false
                MainDeleScreen(navController,isGoogleAccount,{})
            }

            composable(
                route = AppScreens.MainTeacherScreen.route +"/{isGoogleAccount}",
                arguments = listOf(navArgument(name = "isGoogleAccount"){
                    type = NavType.BoolType
                })

            ) {
                val isGoogleAccount = it.arguments?.getBoolean("isGoogleAccount")?:false
                MainTeacherScreen(navController,isGoogleAccount,{})
            }


            composable(
                route = AppScreens.FaceRecognizerScreen.route + "/{error}"+"/{isGoogleAccount}",
                arguments = listOf(navArgument(name = "error") {
                    type = NavType.BoolType
                },
                    navArgument(name = "isGoogleAccount") {
                        type = NavType.BoolType
                    })
            )
            {
                val errorParam = it.arguments?.getBoolean("error") ?: false
                val isGoogleAccount = it.arguments?.getBoolean("isGoogleAccount") ?: false
                FaceRecognizerScreen(navController, errorParam, isGoogleAccount)
            }

            composable(route = AppScreens.RecognizingScreen.route + "/{isGoogleAccount}",
                arguments = listOf(
                    navArgument(name = "isGoogleAccount") {
                        type = NavType.BoolType
                    })
            ) {
                val isGoogleAccount = it.arguments?.getBoolean("error") ?: false
                RecognizingScreen(navController, isGoogleAccount)
            }

        }

        /*
            *  SUBMODULE OF HOME
            * */

        composable(route = ItemMenu.HomeScreen.routeStudent) {
            HomeScreen(clockViewModel)
        }
        composable(route = ItemMenu.HomeScreen.routeDele) {
            HomeScreen(clockViewModel)
        }
        composable(route = ItemMenu.HomeScreen.routeTeacher) {
            HomeScreen(clockViewModel)
        }
        /*
        *  SUBMODULE OF ATTENDANCE
        * */

        composable(route = ItemMenu.AttendanceScreen.routeStudent) {
            AttendanceStudentScreen(navController)
        }
        composable(route = ItemMenu.AttendanceScreen.routeDele) {
            AttendanceStudentScreen(navController)
        }
        composable(route = ItemMenu.AttendanceScreen.routeTeacher) {
            AttendanceTeacherScreen(navController)
        }

        // Sub screens
        composable(
            route = InternalScreens.TodayAttendanceStudentScreen.route + "/{takeAttendance}",
            arguments = listOf(
                navArgument(name = "takeAttendance") {
                    type = NavType.IntType
                }
            )
        ) {
            val takeAttendance = it.arguments?.getInt("takeAttendance") ?: 0
            if(takeAttendance == 1){
                todayAttendanceStudentViewModel.takeAttendance()
            }
            TodayAttendanceStudentScreen(navController, todayAttendanceStudentViewModel)
        }

        composable(route = InternalScreens.AttendanceReportStudentScreen.route) {
            AttendanceReportStudentScreen(navController, attendanceReportStudentViewModel)
        }
        composable(route = InternalScreens.VerifyingIdentityStudentScreen.route+"/{idCourse}" + "/{idSubPart}",
            arguments = listOf(
                navArgument(name = "idCourse") {
                    type = NavType.IntType
                },navArgument(name = "idSubPart") {
                    type = NavType.IntType
                })
        ) {
            val idCourse = it.arguments?.getInt("idCourse") ?: -1
            val idSubPart = it.arguments?.getInt("idSubPart") ?: -1
            verifyingIdentityStudentViewModel.updateCourseRecognition(idCourse,idSubPart)
            VerifyingIdentityStudentScreen(navController,verifyingIdentityStudentViewModel)
        }

        composable(
            route = InternalScreens.AttendanceListTeacherScreen.route + "/{idCourse}" + "/{idSubPart}",
            arguments = listOf(
                navArgument(name = "idCourse") {
                    type = NavType.IntType
                },navArgument(name = "idSubPart") {
                    type = NavType.IntType
                })

        ) {
            val idCourse = it.arguments?.getInt("idCourse") ?: -1
            val idSubPart = it.arguments?.getInt("idSubPart") ?: -1
            attendanceListTeacherViewModel.updateShowedAttendanceList(idCourse,idSubPart)
            AttendanceListTeacherScreen(navController, attendanceListTeacherViewModel)
        }

        composable(route = InternalScreens.AttendanceReportTeacherScreen.route) {
            AttendanceReportTeacherScreen(navController,attendanceReportTeacherViewModel)
        }
        composable(route = InternalScreens.CurseReportTeacherScreen.route + "/{idCourse}",
            arguments = listOf(
                navArgument(name = "idCourse") {
                    type = NavType.IntType
                })
        ) {
            val idCourse = it.arguments?.getInt("idCourse") ?: -1
            print("traspaso: $idCourse")
            curseReportTeacherViewModel.updateTeacherReport(idCourse)
            CurseReportTeacherScreen(navController, curseReportTeacherViewModel)
        }
        composable(
            route = InternalScreens.CurseReportStudentScreen.route +"/{idCourse}",
            arguments = listOf(
                navArgument(name = "idCourse") {
                    type = NavType.IntType
                })
        ) {
            val idCourse = it.arguments?.getInt("idCourse") ?: 0
            curseReportStudentViewModel.updateShowedCourse(idCourse)
            CurseReportStudentScreen(navController,curseReportStudentViewModel)
        }

        composable(route = InternalScreens.TodayAttendanceTeacherScreen.route) {

            TodayAttendanceTeacherScreen(navController,todayAttendanceTeacherViewModel)
        }

        /*
        *  SUBMODULE OF SCHEDULE
        */

        composable(route = ItemMenu.ScheduleScreen.routeStudent) {
            ScheduleScreen(navController, scheduleViewModel)
        }
        composable(route = ItemMenu.ScheduleScreen.routeDele) {
            ScheduleScreen(navController, scheduleViewModel)
        }
        composable(route = ItemMenu.ScheduleScreen.routeTeacher) {
            ScheduleScreen(navController, scheduleViewModel)
        }

        // Sub screens
        composable(route = InternalScreens.AddReminderScreen.route) {
            AddReminderScreen(navController, scheduleViewModel)
        }

        composable(
            route = InternalScreens.EditReminderScreen.route + "/{idReminder}",
            arguments = listOf(
                navArgument(name = "idReminder") {
                    type = NavType.IntType
                })
        ) {
            val idReminder = it.arguments?.getInt("idReminder") ?: 0
            EditReminderScreen(navController, scheduleViewModel, idReminder)
        }


        composable(
            route = InternalScreens.HorarioScreen.route + "/{idCourse}",
            arguments = listOf(
                navArgument(name = "idCourse") {
                    type = NavType.IntType
                })
        ) {
            val idCourse = it.arguments?.getInt("idCourse") ?: 0
            HorarioScreen(navController, idCourse, scheduleViewModel)
        }
        composable(
            route = InternalScreens.LocationScreen.route+ "/{idCourse}",
            arguments = listOf(
                navArgument(name = "idCourse") {
                    type = NavType.IntType
                })
        ) {
            val idCourse = it.arguments?.getInt("idCourse") ?: 0
            LocationScreen(navController, idCourse =idCourse, scheduleViewModel = scheduleViewModel)
        }

        /*
        *  SUBMODULE OF EVENTS
        * */
        composable(route = ItemMenu.EventsScreen.routeStudent) {
            EventsNormalScreen(navController)
        }
        composable(route = ItemMenu.EventsScreen.routeDele) {
            EventsScreen(navController)
        }
        //Sub screens
        composable(route = InternalScreens.AddEventScreen.route) {
            AddEventScreen(navController)
        }

        composable(route = InternalScreens.AddNewScreen.route) {
            AddNewScreen(navController)
        }

        composable(route = InternalScreens.DetailsEventScreen.route) {
            DetailsEventScreen(navController)
        }
        composable(route = InternalScreens.EditEventScreen.route) {
            EditEventScreen(navController)
        }
        composable(route = InternalScreens.EditNewScreen.route) {
            EditNewScreen(navController)
        }

        composable(route = InternalScreens.DetailsEventNormalScreen.route) {
            DetailsEventNormalScreen(navController)
        }
        composable(route = ItemMenu.EventsScreen.routeTeacher) {
            EventsNormalScreen(navController)
        }

        /*
        *  SUBMODULE OF OTHERS
        * */
        composable(route = DrawerItem.SettingsScreen.routeStudent) {
            SettingsScreen()
        }
        composable(route = DrawerItem.SettingsScreen.routeTeacher) {
            SettingsScreen()
        }
        composable(route = DrawerItem.SettingsScreen.routeDele) {
            SettingsScreen()
        }
        composable(route = DrawerItem.HelpScreen.routeStudent) {
            HelpScreen()
        }
        composable(route = DrawerItem.HelpScreen.routeTeacher) {
            HelpScreen()
        }
        composable(route = DrawerItem.HelpScreen.routeDele) {
            HelpScreen()
        }

    }


}