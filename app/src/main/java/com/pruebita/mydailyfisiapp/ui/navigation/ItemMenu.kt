package com.pruebita.mydailyfisiapp.ui.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import com.pruebita.mydailyfisiapp.R

sealed class ItemMenu(
    val icon: Int,
    val title: String,
    val routeStudent: String,
    val routeDele: String,
    val routeTeacher: String,
){
    object HomeScreen: ItemMenu(
        icon = R.drawable.casa,
        title = "Inicio",
        routeStudent = "home_student_screen",
        routeDele = "home_dele_screen",
        routeTeacher = "home_teacher_screen"
    )
    object AttendanceScreen: ItemMenu(
        icon = R.drawable.asistencia,
        title ="Asistencia",
        routeStudent ="attendance_student_screen",
        routeDele ="attendance_dele_screen",
        routeTeacher ="attendance_teacher_screen"
    )
    object ScheduleScreen: ItemMenu(
        icon = R.drawable.horario,
        title ="Horario",
        routeStudent ="schedule_student_screen",
        routeDele ="schedule_dele_screen",
        routeTeacher ="schedule_teacher_screen"
    )

    object EventsScreen: ItemMenu(
        icon = R.drawable.evento,
        title ="Eventos",
        routeStudent ="events_student_screen",
        routeDele ="events_dele_screen",
        routeTeacher ="events_teacher_screen"
    )

}
