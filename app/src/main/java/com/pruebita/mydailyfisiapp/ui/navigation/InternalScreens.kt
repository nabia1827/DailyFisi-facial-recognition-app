package com.pruebita.mydailyfisiapp.ui.navigation

sealed class InternalScreens(val route: String){
    object TodayScreen: InternalScreens("today_screen")
    object AttendanceReportScreen: InternalScreens("attendance_report_screen")


}
