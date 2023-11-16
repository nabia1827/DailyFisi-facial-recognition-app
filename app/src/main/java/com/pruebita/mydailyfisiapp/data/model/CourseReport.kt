package com.pruebita.mydailyfisiapp.data.model

import java.util.Calendar

data class CourseReport(
    val idCourse: Int = -1,
    val courseName: String = "",
    val startTime: Calendar,
    val endTime: Calendar,
    var totalAssist: Int,
    var percentage: Int,
)
