package com.pruebita.mydailyfisiapp.data.model.domain

import java.util.Calendar

data class Semester(
    val idSemester:Int,
    val semesterDesc:String,
    val startDate: Calendar?,
    val endDate: Calendar?
)
