package com.pruebita.mydailyfisiapp.data.repository.interfaces

import com.pruebita.mydailyfisiapp.data.model.Attendance

interface AttendanceRepository {
    fun getTodayAssists(idUser: Int):MutableList<Attendance>
    fun isAttendanceOpen(idCourse:Int):Boolean
}