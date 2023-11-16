package com.pruebita.mydailyfisiapp.data.model

import java.util.Calendar

data class DailyCourseAssist(
    val date: Calendar?,
    var theoryAssist: Boolean?,
    var labAssist: Boolean?
)