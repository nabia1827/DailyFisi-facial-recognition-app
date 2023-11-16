package com.pruebita.mydailyfisiapp.data.model

import java.util.Calendar

data class SubPart(
    val idPart: Int = 0,
    val desPart: String = "Teoria",
    val startHour: Calendar = Calendar.getInstance(),
    val endHour: Calendar = Calendar.getInstance(),
    val room: Room = Room(),
)