package com.pruebita.mydailyfisiapp.data.model.domain

import java.util.Calendar

data class SubPartSummary(
    var idCourse: Int = 0,
    var courseName: String = "course",
    var subpart: String = "",
    var section: Int = 0,
    var startDate: Calendar? = null, //start hour
    var endDate: Calendar? = null, // end hour
    var isFinished: Boolean = false,
)