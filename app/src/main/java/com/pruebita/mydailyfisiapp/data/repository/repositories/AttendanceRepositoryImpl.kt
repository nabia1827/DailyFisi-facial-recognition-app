package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.Attendance
import com.pruebita.mydailyfisiapp.data.model.domain.CourseReport
import com.pruebita.mydailyfisiapp.data.model.domain.DailyCourseAssist
import com.pruebita.mydailyfisiapp.data.model.domain.UpdatedReport
import com.pruebita.mydailyfisiapp.data.repository.interfaces.AttendanceRepository
import java.util.Calendar
import java.util.TimeZone
import kotlin.math.abs

class AttendanceRepositoryImpl:AttendanceRepository {
    private val initMin = 56
    private val initHour = 15
    val day = 16
    private val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")
    override fun getTodayAssists(idUser: Int): MutableList<Attendance> {

        val now = Calendar.getInstance(timeZone)
        // API has to return today courses later than actual hour

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

        // API has to return today courses later than actual hour
        val initMin = 0
        val initHour = 23
        val now = Calendar.getInstance(timeZone)
        val openTime = Calendar.getInstance(timeZone)
        openTime.set(2023, Calendar.NOVEMBER, 10, initHour, initMin, 0)
        val remaining = openTime.timeInMillis - now.timeInMillis
        return true
    }

    override fun getTotalPercentageAttendance(idUser: Int): Int {
        return 98
    }

    override fun getCourseReports(idUser: Int): MutableList<CourseReport> {
        val now = Calendar.getInstance(timeZone)
        // API has to return today courses later than actual hour

        val calculoTeoStart = Calendar.getInstance(timeZone)
        calculoTeoStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin, 0)
        val calculoLabEnd = Calendar.getInstance(timeZone)
        calculoLabEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+3, 0)


        val algoTeoStart = Calendar.getInstance(timeZone)
        algoTeoStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+3, 0)
        val algoLabEnd = Calendar.getInstance(timeZone)
        algoLabEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin +6, 0)

        val marketingTeoStart = Calendar.getInstance(timeZone)
        marketingTeoStart.set(2023, Calendar.NOVEMBER, day, initHour, initMin+6, 0)
        val marketingLabEnd = Calendar.getInstance(timeZone)
        marketingLabEnd.set(2023, Calendar.NOVEMBER, day, initHour, initMin+9, 0)

        return mutableListOf(
            CourseReport(
                idCourse = 1,
                courseName = "Calculo",
                startTime = calculoTeoStart,
                endTime = calculoLabEnd,
                totalAssist = 35,
                percentage = 90
            ),
            CourseReport(
                idCourse = 1,
                courseName = "Algoritmica I",
                startTime = algoTeoStart,
                endTime = algoLabEnd,
                totalAssist = 36,
                percentage = 89
            ),
            CourseReport(
                idCourse = 1,
                courseName = "Marketing",
                startTime = marketingTeoStart,
                endTime = marketingLabEnd,
                totalAssist = 37,
                percentage = 88
            )
        )
    }

    override fun getTodayCourses(idUser: Int): MutableList<Int> {
        return mutableListOf(0,1,2) //Positions not ID
    }

    override fun getTotalFraction(idUser: Int): Pair<Int, Int> {
        return Pair(65,70)
    }

    override fun getUpdatedCourseReport(idUser: Int, idCourse: Int): UpdatedReport? {
        val now = Calendar.getInstance(timeZone)
        val firstChange = Calendar.getInstance(timeZone)
        firstChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+2, 0)

        val secondChange = Calendar.getInstance(timeZone)
        secondChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+4, 0)

        val thirdChange = Calendar.getInstance(timeZone)
        thirdChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+8, 0)
        val remaining1 = firstChange.timeInMillis - now.timeInMillis
        val remaining2 = secondChange.timeInMillis - now.timeInMillis
        val remaining3 = thirdChange.timeInMillis - now.timeInMillis

        if(abs(remaining1) <3000){
            return UpdatedReport(
                percentage = 10,
                totalAssistClasses = 110,
                totalClasses = 130,
                totalCourse = 45,
                percentageCourse = 99
            )
        }
        else if(abs(remaining2) <3000){
            return UpdatedReport(
                percentage = 20,
                totalAssistClasses = 120,
                totalClasses = 130,
                totalCourse = 46,
                percentageCourse = 99
            )

        }else if(abs(remaining3) <3000){
            return UpdatedReport(
                percentage = 30,
                totalAssistClasses = 130,
                totalClasses = 130,
                totalCourse = 47,
                percentageCourse = 99
            )

        }else{
            return null
        }
    }

    override fun getUserCourseAttendance(
        idUser: Int,
        idCourse: Int
    ): MutableList<DailyCourseAssist> {
        return mutableListOf(
            DailyCourseAssist(
                date = Calendar.getInstance(),
                theoryAssist = true,
                labAssist = true
            ),
            DailyCourseAssist(
                date = Calendar.getInstance(),
                theoryAssist = true,
                labAssist = true
            ),
            DailyCourseAssist(
                date = Calendar.getInstance(),
                theoryAssist = true,
                labAssist = true
            ),
            DailyCourseAssist(
                date = Calendar.getInstance(),
                theoryAssist = false,
                labAssist = true
            ),
            DailyCourseAssist(
                date = Calendar.getInstance(),
                theoryAssist = false,
                labAssist = false
            ),
            DailyCourseAssist(
                date = Calendar.getInstance(),
                theoryAssist = true,
                labAssist = true
            )
        )
    }

    override fun getActualCourseAssists(idUser: Int, idCourse: Int): Pair<Boolean, Boolean> {
        return Pair(true, true)
    }

}