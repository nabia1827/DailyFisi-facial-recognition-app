package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.domain.Attendance
import com.pruebita.mydailyfisiapp.data.model.domain.CourseReport
import com.pruebita.mydailyfisiapp.data.model.domain.DailyCourseAssist
import com.pruebita.mydailyfisiapp.data.model.domain.StudentAssistUnit
import com.pruebita.mydailyfisiapp.data.model.domain.SubPart
import com.pruebita.mydailyfisiapp.data.model.domain.UpdatedReport

interface AttendanceRepository {
    fun getTodayAssists(idUser: Int):MutableList<Attendance>
    fun isAttendanceOpen(idCourse:Int):Int

    fun getTotalPercentageAttendance(idUser:Int):Int

    fun getCourseReports(idUser:Int):MutableList<CourseReport>

    fun getTodayCourses(idUser:Int):MutableList<Int>

    fun getTotalFraction(idUser:Int):Pair<Int, Int>

    fun getUpdatedCourseReport(idUser: Int, idCourse: Int): UpdatedReport?

    fun getUserCourseAttendance(idUser: Int,idCourse: Int):MutableList<DailyCourseAssist>

    fun getStudentList(idCourse: Int): MutableList<StudentAssistUnit>

    fun getAttendanceList(idCourse: Int, idSubPart: Int): MutableList<Boolean>

    fun endAttendance(idCourse: Int, idSubPart: Int)

    fun setAttendanceUser(idUser:Int, idCourse:Int, idSubPart:Int, assist:Boolean)

    fun getSectionTheoryReport(idCourse: Int):MutableList<Int>
    fun getSectionLabReport(idCourse: Int):MutableList<Int>


}