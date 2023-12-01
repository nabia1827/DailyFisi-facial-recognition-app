package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.Semester
import com.pruebita.mydailyfisiapp.data.repository.interfaces.SemesterRepository
import com.pruebita.mydailyfisiapp.data.source.getDate
import java.util.Calendar
import java.util.TimeZone

class SemesterRepositoryImpl:SemesterRepository {
    private val currentSem = Semester(
        idSemester = 1,
        semesterDesc = "2023-II",
        getDate(2023,9,30,8,0),
        getDate(2024,1,19,0,0)
    )
    private val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")

    override fun getCurrentSemester():Semester{
        return currentSem
    }

    override fun getCurrentWeek(): Int {
        val initDate: Calendar = currentSem.startDate
        val now = Calendar.getInstance(timeZone)
        val millisRemaining = now.timeInMillis - initDate.timeInMillis

        return (millisRemaining / (7 * 24 * 60 * 60 * 1000)).toInt()
    }
}