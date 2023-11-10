package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.Attendance
import com.pruebita.mydailyfisiapp.data.model.DateManager
import com.pruebita.mydailyfisiapp.data.model.Event
import com.pruebita.mydailyfisiapp.data.model.User
import com.pruebita.mydailyfisiapp.data.model.UserManager
import com.pruebita.mydailyfisiapp.data.repository.repositories.AttendanceRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.CourseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class TodayAttendanceStudentViewModel
@Inject constructor(private val context: Context): ViewModel() {
    private val repoAssists: AttendanceRepositoryImpl = AttendanceRepositoryImpl()
    private val userManager: UserManager = UserManager(context)


    private val _dateManager = MutableLiveData<DateManager>()
    val dateManager: LiveData<DateManager> = _dateManager

    private val _todayAssists = MutableLiveData<MutableList<Attendance>>()
    val todayAssists: LiveData<MutableList<Attendance>> = _todayAssists

    private val _currentClassEndTime = MutableLiveData<Calendar>(null)
    val currentClassEndTime: LiveData<Calendar> = _currentClassEndTime

    private val _nextClassStartTime = MutableLiveData<Calendar>(null)
    val nextClassStartTime: LiveData<Calendar> = _nextClassStartTime

    private var currentIndex = -1

    init{
        updateAssistsData()
        val timeZone = TimeZone.getTimeZone("America/Lima")
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timer = Timer()
        val now = Calendar.getInstance(timeZone)
        val timeToMidnight:Long = ((24 - now.get(Calendar.HOUR_OF_DAY)) * 60 * 60 * 1000).toLong()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                updateAssistsData()
            }
        }, timeToMidnight, 24 * 60 * 60 * 1000)

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val calendar = Calendar.getInstance(timeZone)
                val nextTime =_nextClassStartTime.value
                if(nextTime != null){
                    val remaining = nextTime.timeInMillis - calendar.timeInMillis
                    if(remaining <= 0){
                        moveToNextAssist()
                    }

                }



            }
        }, 0, 1000) // Actualizar cada segundo
    }

    fun updateAssistsData(){
        _todayAssists.value = repoAssists.getTodayAssists(userManager.getIdUser())
        val todayAss = _todayAssists.value
        if(todayAss != null){

            _nextClassStartTime.value = todayAss[0].startTime

            val nextTime =_nextClassStartTime.value

            if(nextTime != null){
                val remaining = nextTime.timeInMillis - Calendar.getInstance().timeInMillis
                if(remaining <= 0){
                    currentIndex += 1

                    //In next reps refresh past states
                    todayAss[currentIndex].state = 2
                    _currentClassEndTime.value = todayAss[currentIndex].endTime
                    _nextClassStartTime.value = todayAss[currentIndex + 1].startTime
                    _todayAssists.value = todayAss

                }

            }
        }
    }

    fun moveToNextAssist(){
        val todayAss = _todayAssists.value
        if(todayAss != null && (currentIndex + 1) < todayAss.size){
            currentIndex += 1
        }
    }


}