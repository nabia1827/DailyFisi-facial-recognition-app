package com.pruebita.mydailyfisiapp.data.model

import java.util.Calendar

data class Semester(
    val idSemester:Int,
    val semesterDesc:String,
    val startDate: Calendar,
    val endDate: Calendar
)
