package com.pruebita.mydailyfisiapp.data.model

import java.util.Calendar

data class Attendance(
    val date: Calendar,
    val idCourse: Int = 0,
    val courseName:String = "",
    val coursePart:String = "Teoria",
    val courseRoom:String ="",
    val startTime: Calendar,
    val endTime: Calendar,
    var state:Int = 3, // (1) Past (2)Now (3) Later
)