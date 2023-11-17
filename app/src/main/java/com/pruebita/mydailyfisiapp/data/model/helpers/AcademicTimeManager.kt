package com.pruebita.mydailyfisiapp.data.model.helpers

import android.content.Context
import android.content.SharedPreferences
import com.pruebita.mydailyfisiapp.data.model.domain.Semester

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

        return Semester(idSemester, semesterDesc.toString(), null, null)
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