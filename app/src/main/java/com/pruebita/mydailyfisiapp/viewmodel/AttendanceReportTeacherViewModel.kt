package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.domain.CourseReport
import com.pruebita.mydailyfisiapp.data.model.helpers.AcademicTimeManager
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.repositories.AttendanceRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class AttendanceReportTeacherViewModel
@Inject constructor(private val context: Context): ViewModel(){
    private val repoAssists: AttendanceRepositoryImpl = AttendanceRepositoryImpl()
    private val userManager: UserManager = UserManager(context)
    private val academicTimeManager: AcademicTimeManager = AcademicTimeManager(context)

    private val _totalPercentage = MutableLiveData<Int>(0)
    val totalPercentage: LiveData<Int> = _totalPercentage

    private val _totalClasses = MutableLiveData<Int>(0)
    val totalClasses: LiveData<Int> = _totalClasses

    private val _totalAssistClasses = MutableLiveData<Int>(0)
    val totalAssistClasses : LiveData<Int> = _totalAssistClasses

    private val _semester = MutableLiveData<String>(academicTimeManager.getCurrentSemester().semesterDesc) //We have to do a Semester Manager - local
    val semester : LiveData<String> = _semester

    private val _courseReports = MutableLiveData<MutableList<CourseReport>>()
    val courseReports: LiveData<MutableList<CourseReport>> = _courseReports

    private val _todayCourses = MutableLiveData<MutableList<Int>>()
    val todayCourses: LiveData<MutableList<Int>> = _todayCourses

    init{
        updateCourseReports()
        val timeZone = TimeZone.getTimeZone("America/Lima")
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timer = Timer()
        val start = Calendar.getInstance(timeZone)
        val timeToMidnight:Long = ((24 - start.get(Calendar.HOUR_OF_DAY)) * 60 * 60 * 1000).toLong()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                updateCourseReports()
            }
        }, timeToMidnight, 24 * 60 * 60 * 1000)
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val now = Calendar.getInstance(timeZone)
                val reports = _courseReports.value?.toMutableList()
                if(reports != null){
                    for (i in 0 until reports.size){
                        val indexesToday = _todayCourses.value
                        if(indexesToday != null){
                            if(i in indexesToday){
                                if(isCurrentCourse(reports[i].startTime,reports[i].endTime,now)){
                                    updateForChanges(reports, i)

                                    //println(_courseReports.value?.get(i)?.totalAssist ?: 5)
                                }


                            }
                        }


                    }
                }

            }
        }, 0, 3000) // Actualizar cada 3 segundo
    }

    fun updateCourseReports(){
        _courseReports.postValue(repoAssists.getCourseReports(userManager.getIdUser()))
        _todayCourses.postValue(repoAssists.getTodayCourses(userManager.getIdUser()))
        _totalPercentage.postValue(repoAssists.getTotalPercentageAttendance(userManager.getIdUser()))
        val fraction = repoAssists.getTotalFraction(userManager.getIdUser())
        _totalAssistClasses.postValue(fraction.first)
        _totalClasses.postValue(fraction.second)
    }

    fun isCurrentCourse(courseStart: Calendar, courseEnd: Calendar, now: Calendar):Boolean{
        return now.timeInMillis>courseStart.timeInMillis && now.timeInMillis<courseEnd.timeInMillis
    }
    fun updateForChanges(reports:MutableList<CourseReport>, i:Int){
        val updated = repoAssists.getUpdatedCourseReport(userManager.getIdUser(),reports[i].idCourse)
        if(updated != null){
            reports[i].percentage = updated.percentageCourse
            _courseReports.postValue(reports)
            _totalAssistClasses.postValue(updated.totalAssistClasses)
            _totalClasses.postValue(updated.totalClasses)
            _totalPercentage.postValue(updated.percentage)

        }
    }
}