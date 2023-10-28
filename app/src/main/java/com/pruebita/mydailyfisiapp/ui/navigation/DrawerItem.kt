package com.pruebita.mydailyfisiapp.ui.navigation

import com.pruebita.mydailyfisiapp.R

sealed class DrawerItem(
    val icon: Int,
    val title: String,
    val routeStudent: String,
    val routeDele: String,
    val routeTeacher: String,
){
    object HomeScreen: DrawerItem(
        icon = R.drawable.casa,
        title = "Inicio",
        routeStudent = "home_student_screen",
        routeDele = "home_dele_screen",
        routeTeacher = "home_teacher_screen"
    )
    object AttendanceScreen: DrawerItem(
        icon = R.drawable.ic_asistencias,
        title = "Asistencia",
        routeStudent = "attendance_student_screen",
        routeDele = "attendance_dele_screen",
        routeTeacher = "attendance_teacher_screen"
    )

    object ScheduleScreen: DrawerItem(
        icon = R.drawable.ic_horarios,
        title = "Horario",
        routeStudent = "schedule_student_screen",
        routeDele = "schedule_dele_screen",
        routeTeacher = "schedule_teacher_screen"
    )

    object EventsScreen: DrawerItem(
        icon = R.drawable.ic_eventos,
        title = "Eventos",
        routeStudent = "events_student_screen",
        routeDele = "events_dele_screen",
        routeTeacher = "events_teacher_screen"
    )
    object SettingsScreen: DrawerItem(
        icon = R.drawable.ic_settings,
        title = "Configuracion",
        routeStudent = "settings_student_screen",
        routeDele = "settings_dele_screen",
        routeTeacher = "settings_teacher_screen"
    )
    object HelpScreen: DrawerItem(
        icon = R.drawable.ic_help,
        title ="Ayuda",
        routeStudent ="help_student_screen",
        routeDele = "help_dele_screen",
        routeTeacher = "help_teacher_screen"
    )

}
