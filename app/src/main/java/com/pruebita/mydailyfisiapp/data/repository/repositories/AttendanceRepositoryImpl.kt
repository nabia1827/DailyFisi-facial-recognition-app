package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.Attendance
import com.pruebita.mydailyfisiapp.data.repository.interfaces.AttendanceRepository

class AttendanceRepositoryImpl:AttendanceRepository {
    override fun getTodayAssists(idUser: Int): MutableList<Attendance> {
        return mutableListOf()
    }
}