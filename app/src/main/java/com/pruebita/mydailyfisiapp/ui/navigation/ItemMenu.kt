package com.pruebita.mydailyfisiapp.ui.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import com.pruebita.mydailyfisiapp.R

sealed class ItemMenu(
    val icon: Int,
    val title: String,
    val route: String,
){
    object HomeScreen: ItemMenu(R.drawable.casa,"Inicio", "home_screen")
    object AttendanceScreen: ItemMenu(R.drawable.asistencia,"Asistencia", "attendance_screen")
    object ScheduleScreen: ItemMenu(R.drawable.horario,"Horario", "schedule_screen")

    object EventsScreen: ItemMenu(R.drawable.evento,"Eventos", "events_screen")

}
