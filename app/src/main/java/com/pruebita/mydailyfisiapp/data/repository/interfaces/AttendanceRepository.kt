package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Attendance
import com.pruebita.mydailyfisiapp.data.model.domain.CourseReport
import com.pruebita.mydailyfisiapp.data.model.domain.DailyCourseAssist
import com.pruebita.mydailyfisiapp.data.model.domain.UpdatedReport

interface AttendanceRepository {
    fun getTodayAssists(idUser: Int):MutableList<Attendance>
    fun isAttendanceOpen(idCourse:Int):Boolean

    fun getTotalPercentageAttendance(idUser:Int):Int

    fun getCourseReports(idUser:Int):MutableList<CourseReport>

    fun getTodayCourses(idUser:Int):MutableList<Int>

    fun getTotalFraction(idUser:Int):Pair<Int, Int>

    fun getUpdatedCourseReport(idUser: Int, idCourse: Int): UpdatedReport?

    fun getUserCourseAttendance(idUser: Int,idCourse: Int):MutableList<DailyCourseAssist>

    fun getActualCourseAssists(idUser: Int,idCourse: Int): Pair<Boolean,Boolean>

}