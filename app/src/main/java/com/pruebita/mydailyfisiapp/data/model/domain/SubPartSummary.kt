package com.pruebita.mydailyfisiapp.data.model.domain

import java.util.Calendar

data class SubPartSummary(
    val idCourse: Int = 0,
    val courseName: String = "course",
    val subpart: String = "",
    val section: Int = 0,
    val startDate: Calendar? = null,
    val endDate: Calendar? = null,
    val isFinished: Boolean = false,
)