package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.domain.Attendance
import com.pruebita.mydailyfisiapp.data.model.domain.CourseReport
import com.pruebita.mydailyfisiapp.data.model.domain.DailyCourseAssist
import com.pruebita.mydailyfisiapp.data.model.domain.StudentAssistUnit
import com.pruebita.mydailyfisiapp.data.model.domain.UpdatedReport
import com.pruebita.mydailyfisiapp.data.repository.interfaces.AttendanceRepository
import java.util.Calendar
import java.util.TimeZone
import kotlin.math.abs

class AttendanceRepositoryImpl:AttendanceRepository {
    private val initMin = 55
    private val initHour = 7
    val day = 25
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
                idCourse = 7,
                idSubPart = 0,
                courseName = "Moviles",
                coursePart = "Teoria",
                courseRoom = "102 - NP",
                startTime = calculoTeoStart,
                endTime = calculoTeoEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 7,
                idSubPart = 1,
                courseName = "Moviles",
                coursePart = "Practica",
                courseRoom = "Lab 04 - NP",
                startTime = calculoLabStart,
                endTime = calculoLabEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 8,
                idSubPart = 0,
                courseName = "Calculo",
                coursePart = "Teoria",
                courseRoom = "102 - NP",
                startTime = algoTeoStart,
                endTime = algoTeoEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 8,
                idSubPart = 1,
                courseName = "Calculo",
                coursePart = "Practica",
                courseRoom = "Lab 04 - NP",
                startTime = algoLabStart,
                endTime = algoLabEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 9,
                idSubPart = 0,
                courseName = "Marketing",
                coursePart = "Teoria",
                courseRoom = "102 - NP",
                startTime = marketingTeoStart,
                endTime = marketingTeoEnd,
                state = 5
            ),
            Attendance(
                date = now,
                idCourse = 9,
                idSubPart = 1,
                courseName = "Marketing",
                coursePart = "Practica",
                courseRoom = "Lab 04 - NP",
                startTime = marketingLabStart,
                endTime = marketingLabEnd,
                state = 5
            )
        )
    }

    override fun isAttendanceOpen(idCourse: Int): Int {
        // (1) No taken yet (2)Opening (3) taken
        val now = Calendar.getInstance(timeZone)
        val openTime = Calendar.getInstance(timeZone)
        openTime.set(2023, Calendar.NOVEMBER, 10, initHour, initMin+1, 0)
        val remaining = openTime.timeInMillis - now.timeInMillis
        return 2
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
                section = 1,
                courseName = "Moviles",
                startTime = calculoTeoStart,
                endTime = calculoLabEnd,
                totalAssist = 35,
                percentage = 90
            ),
            CourseReport(
                idCourse = 2,
                section = 5,
                courseName = "Calculo",
                startTime = algoTeoStart,
                endTime = algoLabEnd,
                totalAssist = 36,
                percentage = 89
            ),
            CourseReport(
                idCourse = 3,
                section = 4,
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
        val now = Calendar.getInstance(timeZone)
        val firstChange = Calendar.getInstance(timeZone)
        firstChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+2, 0)

        val remaining1 = firstChange.timeInMillis - now.timeInMillis
        return if(remaining1<=0){
            mutableListOf(
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
                    theoryAssist = false,
                    labAssist = false
                )
            )
        } else{
            mutableListOf(
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

    }

    override fun getStudentList(idCourse: Int): MutableList<StudentAssistUnit> {
        return mutableListOf(
            StudentAssistUnit(
                idStudent = 1,
                names = "Victor Genaro",
                lastNames = "Rosales Cabanillas",
                nick = "VR",
            ),
            StudentAssistUnit(
                idStudent = 2,
                names = "Williams",
                lastNames = "Rodarte Grijalba",
                nick = "WR",
            ),
            StudentAssistUnit(
                idStudent = 3,
                names = "Marco Antonio",
                lastNames = "Escalante Arirama",
                nick = "ME",
            ),StudentAssistUnit(
                idStudent = 4,
                names = "Freddy",
                lastNames = "Flores Dilas",
                nick = "FF",
            ),StudentAssistUnit(
                idStudent = 5,
                names = "Piero Alexander",
                lastNames = "Castro Moran",
                nick = "PC",
            ),StudentAssistUnit(
                idStudent = 6,
                names = "Mireya Lucia",
                lastNames = "Jimenez De la Cruz",
                nick = "MJ",
            ),StudentAssistUnit(
                idStudent = 7,
                names = "Andrea Karin",
                lastNames = "Miranda Sanchez",
                nick = "AM",
            ),StudentAssistUnit(
                idStudent = 8,
                names = "Grissell Lizeth",
                lastNames = "Meza Iglesias",
                nick = "GM",
            ),StudentAssistUnit(
                idStudent = 9,
                names = "Mia Solimar",
                lastNames = "Acosta Cortez",
                nick = "MA",
            )
        )
    }

    override fun getAttendanceList(idCourse: Int, idSubPart: Int): MutableList<Boolean> {
        val now = Calendar.getInstance(timeZone)
        val firstChange = Calendar.getInstance(timeZone)
        firstChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+1, 0)

        val secondChange = Calendar.getInstance(timeZone)
        secondChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+2, 0)

        val remaining1 = firstChange.timeInMillis - now.timeInMillis
        val remaining2 = secondChange.timeInMillis - now.timeInMillis
        return if(remaining1<=0 && remaining2>=0){
            mutableListOf(false,true,false,true,false,true,false,true,false)
        }else if (remaining2<=0){
            mutableListOf(true,true,true,true,true,true,true,true,true)
        }else{
            mutableListOf(false,false,false,false,false,false,false,false,false)
        }

    }

    override fun endAttendance(idCourse: Int, idSubPart: Int) {
        // Send "finish" to API
    }

    override fun setAttendance(idUser: Int, idCourse: Int, idSubPart: Int, assist: Boolean) {
        // Send attendance to API
    }

    override fun getSectionTheoryReport(idCourse: Int): MutableList<Int> {
        val now = Calendar.getInstance(timeZone)
        val firstChange = Calendar.getInstance(timeZone)
        firstChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+1, 0)

        val secondChange = Calendar.getInstance(timeZone)
        secondChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+2, 0)

        val remaining1 = firstChange.timeInMillis - now.timeInMillis
        val remaining2 = secondChange.timeInMillis - now.timeInMillis
        return if(remaining1<=0 && remaining2>=0){
            mutableListOf(5,6,5,5,6,6,5,6,6)
        }else if (remaining2<=0){
            mutableListOf(7,7,7,5,6,7,7,6,6)
        }else{
            mutableListOf(3,3,3,3,3,3,3,3,3)
        }
    }

    override fun getSectionLabReport(idCourse: Int): MutableList<Int> {
        val now = Calendar.getInstance(timeZone)
        val firstChange = Calendar.getInstance(timeZone)
        firstChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+1, 0)

        val secondChange = Calendar.getInstance(timeZone)
        secondChange.set(2023, Calendar.NOVEMBER, day, initHour, initMin+2, 0)

        val remaining1 = firstChange.timeInMillis - now.timeInMillis
        val remaining2 = secondChange.timeInMillis - now.timeInMillis
        return if(remaining1<=0 && remaining2>=0){
            mutableListOf(5,6,5,5,6,6,5,6,6)
        }else if (remaining2<=0){
            mutableListOf(7,7,7,5,6,7,7,6,6)
        }else{
            mutableListOf(3,3,3,3,3,3,3,3,3)
        }
    }


}