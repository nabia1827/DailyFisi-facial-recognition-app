package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Semester

interface SemesterRepository {
    fun getCurrentSemester(): Semester
    fun getCurrentWeek():Int
}