package com.pruebita.mydailyfisiapp.data.model.helpers

import android.content.Context
import android.content.SharedPreferences
import com.pruebita.mydailyfisiapp.data.model.domain.Semester
import com.pruebita.mydailyfisiapp.data.source.getDate

class AcademicTimeManager(private val context: Context) {
    private val semesterData: SharedPreferences =
        context.getSharedPreferences("SemesterData", Context.MODE_PRIVATE)

    private val weekData: SharedPreferences =
        context.getSharedPreferences("WeekData", Context.MODE_PRIVATE)

    fun saveCurrentSemester(semester: Semester) {
        val editor = semesterData.edit()
        editor.putInt("idSemester", semester.idSemester)
        editor.putString("semesterDesc", semester.semesterDesc)
        editor.apply()
    }

    fun saveCurrentWeek(week: Int) {
        val editor = weekData.edit()
        editor.putInt("week", week)
        editor.apply()
    }

    fun getCurrentSemester(): Semester {
        val idSemester = semesterData.getInt("idSemester", 0)
        val semesterDesc = semesterData.getString("semesterDesc", " ")

        return Semester(idSemester, semesterDesc.toString(),
            getDate(2023,9,30,8,0),
            getDate(2024,1,19,0,0))
    }

    fun getCurrentWeek(): Int {
        return weekData.getInt("week", 0)
    }
    fun deleteSemester() {
        val editor = semesterData.edit()
        editor.remove("idSemester")
        editor.remove("semesterDesc")
        editor.apply()
    }

    fun deleteWeek() {
        val editor = weekData.edit()
        editor.remove("week")
        editor.apply()
    }

}