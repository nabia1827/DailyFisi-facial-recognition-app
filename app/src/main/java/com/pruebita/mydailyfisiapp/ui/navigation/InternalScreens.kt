package com.pruebita.mydailyfisiapp.ui.navigation

sealed class InternalScreens(val route: String){
    object TodayScreen: InternalScreens("today_screen")
    object AttendanceReportScreen: InternalScreens("attendance_report_screen")

    object AddEventScreen: InternalScreens("add_event_screen")
    object AddNewScreen: InternalScreens("add_new_screen")
    object EditEventScreen: InternalScreens("edit_event_screen")
    object EditNewScreen: InternalScreens("edit_new_screen")
    object DetailsEventScreen: InternalScreens("details_event_screen")
    object AddReminderScreen: InternalScreens("add_reminder_screen")
    object HorarioScreen: InternalScreens("horario_screen")


}
