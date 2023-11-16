package com.pruebita.mydailyfisiapp.data.model.domain

import java.util.Calendar

data class DailyCourseAssist(
    val date: Calendar?,
    var theoryAssist: Boolean?,
    var labAssist: Boolean?
)