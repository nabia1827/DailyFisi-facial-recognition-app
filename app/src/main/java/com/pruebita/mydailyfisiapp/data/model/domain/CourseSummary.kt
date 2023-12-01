package com.pruebita.mydailyfisiapp.data.model.domain

import java.util.Calendar

data class CourseSummary(
    var idCourse: Int = 0,
    var courseName: String = "course",
    var section: Int = 0,
    var startDate: Calendar? = null,//Start hour
    var endDate: Calendar? = null,//End hour
)