package com.pruebita.mydailyfisiapp.ui.navigation

import com.pruebita.mydailyfisiapp.R

sealed class DrawerItem(
    val icon: Int,
    val title: String,
    val route: String,
){
    object HomeScreen: DrawerItem(R.drawable.casa,"Inicio", "home_screen")
    object AttendanceScreen: DrawerItem(R.drawable.ic_asistencias,"Asistencia", "attendance_screen")
    object ScheduleScreen: DrawerItem(R.drawable.ic_horarios,"Horario", "schedule_screen")

    object EventsScreen: DrawerItem(R.drawable.ic_eventos,"Eventos", "events_screen")
    object SettingsScreen: DrawerItem(R.drawable.ic_settings,"Configuracion", "settings_screen")
    object HelpScreen: DrawerItem(R.drawable.ic_help,"Ayuda", "help_screen")

}
