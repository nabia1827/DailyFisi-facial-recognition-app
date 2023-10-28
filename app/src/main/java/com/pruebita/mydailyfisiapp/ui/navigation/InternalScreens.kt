package com.pruebita.mydailyfisiapp.ui.navigation

sealed class InternalScreens(val route: String){
    /*
    *  SUBMODULE OF ATTENDANCE
    * */

    object TodayAttendanceStudentScreen: InternalScreens("today_attendance_student_screen")
    object AttendanceReportStudentScreen: InternalScreens("attendance_report_student_screen")
    object VerifyingIdentityStudentScreen: InternalScreens("verifying_identity_student_screen")
    object AttendanceListTeacherScreen: InternalScreens("attendance_list_teacher_screen")
    object AttendanceReportTeacherScreen: InternalScreens("attendance_report_teacher_screen")
    object CurseReportTeacherScreen: InternalScreens("curse_report_teacher_screen")
    object TodayAttendanceTeacherScreen: InternalScreens("today_attendance_teacher_screen")

    /*
    *  SUBMODULE OF SCHEDULE
    * */

    object AddReminderScreen: InternalScreens("add_reminder_screen")
    object HorarioScreen: InternalScreens("horario_screen")
    object LocationScreen: InternalScreens("location_screen")


    /*
    *  SUBMODULE OF EVENTS
    * */

    object AddEventScreen: InternalScreens("add_event_screen")
    object AddNewScreen: InternalScreens("add_new_screen")
    object EditEventScreen: InternalScreens("edit_event_screen")
    object EditNewScreen: InternalScreens("edit_new_screen")
    object DetailsEventScreen: InternalScreens("details_event_screen")
    object DetailsEventNormalScreen: InternalScreens("details_event_normal_screen")




}
