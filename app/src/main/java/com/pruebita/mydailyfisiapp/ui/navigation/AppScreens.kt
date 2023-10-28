package com.pruebita.mydailyfisiapp.ui.navigation

sealed class AppScreens(val route: String){
    object SplashScreen: AppScreens("splash_screen")
    object StartScreen: AppScreens("start_screen")
    object LoginScreen: AppScreens("login_screen")
    object MainStudentScreen: AppScreens("main_student_screen")
    object MainDeleScreen: AppScreens("main_dele_screen")
    object MainTeacherScreen: AppScreens("main_teacher_screen")

    object FaceRecognizerScreen: AppScreens("face_recognizer_screen")

    object RecognizingScreen: AppScreens("recognizing_screen")

}
