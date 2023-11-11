package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.Attendance
import com.pruebita.mydailyfisiapp.data.repository.interfaces.AttendanceRepository
import java.util.Calendar
import java.util.TimeZone

class AttendanceRepositoryImpl:AttendanceRepository {
    override fun getTodayAssists(idUser: Int): MutableList<Attendance> {

        val timeZone = TimeZone.getTimeZone("America/Lima")
        val now = Calendar.getInstance(timeZone)
        // API has to return today courses later than actual hour
        val initMin = 37
        val initHour = 0
        val day = 11

        val calculoTeoStart = Calendar.getInstance(timeZone)
        calculoTeoStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin, 0)
        val calculoTeoEnd = Calendar.getInstance(timeZone)
        calculoTeoEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+1, 0)

        val calculoLabStart = Calendar.getInstance(timeZone)
        calculoLabStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+1, 0)
        val calculoLabEnd = Calendar.getInstance(timeZone)
        calculoLabEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+3, 0)


        val algoTeoStart = Calendar.getInstance(timeZone)
        algoTeoStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+3, 0)
        val algoTeoEnd = Calendar.getInstance(timeZone)
        algoTeoEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+4, 0)

        val algoLabStart = Calendar.getInstance(timeZone)
        algoLabStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin +4, 0)
        val algoLabEnd = Calendar.getInstance(timeZone)
        algoLabEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin +6, 0)


        val marketingTeoStart = Calendar.getInstance(timeZone)
        marketingTeoStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+6, 0)
        val marketingTeoEnd = Calendar.getInstance(timeZone)
        marketingTeoEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+7, 0)

        val marketingLabStart = Calendar.getInstance(timeZone)
        marketingLabStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+7, 0)
        val marketingLabEnd = Calendar.getInstance(timeZone)
        marketingLabEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+9, 0)

        return mutableListOf(
            Attendance(
                date = now,
                idCourse = 1,
                courseName = "Calculo",
                coursePart = "Teoria",
                courseRoom = "102 - NP",
                startTime = calculoTeoStart,
                endTime = calculoTeoEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 1,
                courseName = "Calculo",
                coursePart = "Practica",
                courseRoom = "Lab 04 - NP",
                startTime = calculoLabStart,
                endTime = calculoLabEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 1,
                courseName = "Algoritmica I",
                coursePart = "Teoria",
                courseRoom = "102 - NP",
                startTime = algoTeoStart,
                endTime = algoTeoEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 1,
                courseName = "Algoritmica I",
                coursePart = "Practica",
                courseRoom = "Lab 04 - NP",
                startTime = algoLabStart,
                endTime = algoLabEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 1,
                courseName = "Marketing",
                coursePart = "Teoria",
                courseRoom = "102 - NP",
                startTime = marketingTeoStart,
                endTime = marketingTeoEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 1,
                courseName = "Marketing",
                coursePart = "Practica",
                courseRoom = "Lab 04 - NP",
                startTime = marketingLabStart,
                endTime = marketingLabEnd,
                state = 5
            )
        )
    }

    override fun isAttendanceOpen(idCourse: Int): Boolean {
        val timeZone = TimeZone.getTimeZone("America/Lima")
        // API has to return today courses later than actual hour
        val initMin = 0
        val initHour = 23
        val now = Calendar.getInstance(timeZone)
        val openTime = Calendar.getInstance(timeZone)
        openTime.set(2023, Calendar.NOVEMBER, 10, initHour, initMin, 0)
        val remaining = openTime.timeInMillis - now.timeInMillis
        return false
    }
}