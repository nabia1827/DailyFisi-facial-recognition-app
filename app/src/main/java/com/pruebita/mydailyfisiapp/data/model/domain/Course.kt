package com.pruebita.mydailyfisiapp.data.model.domain

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.Calendar
data class Course(
    val idCourse: Int = 0, //Unique no matter what
    val courseName: String = "course",
    val startDate: Calendar? = null,
    val endDate: Calendar? = null,
    val term: String = "",//ciclo
    val theoryPart: SubPart = SubPart(),
    val labPart: SubPart = SubPart(),
    val teacherFullName: String? = "Someone",
    val section: Int = 0
)