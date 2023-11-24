package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.domain.Attendance
import com.pruebita.mydailyfisiapp.data.model.helpers.DateManager
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
class TodayAttendanceStudentViewModel
@Inject constructor(private val context: Context): ViewModel() {
    private val repoAssists: AttendanceRepositoryImpl = AttendanceRepositoryImpl()
    private val userManager: UserManager = UserManager(context)

    //val todayAssists2 = mutableStateOf<MutableList<Attendance>>(mutableListOf()) // Cambiado a MutableState
    private val _dateManager = MutableLiveData<DateManager>(DateManager())
    val dateManager: LiveData<DateManager> = _dateManager

    private val _todayAssists = MutableLiveData<MutableList<Attendance>>()
    val todayAssists: LiveData<MutableList<Attendance>> = _todayAssists //Default: all 5

    private val _currentClassEndTime = MutableLiveData<Calendar>(null)
    val currentClassEndTime: LiveData<Calendar> = _currentClassEndTime

    private val _nextClassStartTime = MutableLiveData<Calendar>(null)
    val nextClassStartTime: LiveData<Calendar> = _nextClassStartTime

    private val _isFinished = MutableLiveData<Boolean>(true)
    val isFinished: LiveData<Boolean> = _isFinished

    private val _cont = MutableLiveData<Int>(0)
    val cont: LiveData<Int> = _cont

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

                if(_isFinished.value != true){
                    val now = Calendar.getInstance(timeZone)
                    // Attendance is available
                    val todayAss = _todayAssists.value?.toMutableList()
                    if (todayAss != null && currentIndex!=-1){
                        if(repoAssists.isAttendanceOpen(todayAss[currentIndex].idCourse)==2 && todayAss[currentIndex].state == 4){
                            todayAss[currentIndex].state = 3 //Open
                            _todayAssists.postValue(todayAss)
                        }else if(repoAssists.isAttendanceOpen(todayAss[currentIndex].idCourse)==3 && todayAss[currentIndex].state != 1){
                            todayAss[currentIndex].state = 2 //You lost it
                        }
                    }

                    // Move on or not?
                    val endTime =_currentClassEndTime.value
                    if(endTime != null){
                        val remaining1 = endTime.timeInMillis - now.timeInMillis
                        if(remaining1 <= 0){

                            val todayAss = _todayAssists.value?.toMutableList()
                            if(currentIndex != -1){
                                if(todayAss != null && todayAss[currentIndex].state != 1){
                                    todayAss[currentIndex].state = 2 //Broadcast real time
                                    _todayAssists.postValue(todayAss)
                                }
                            }

                            val nextTime =_nextClassStartTime.value
                            if(nextTime != null){
                                val remaining2 = nextTime.timeInMillis - now.timeInMillis
                                if(remaining2 <= 0){
                                    moveToNextAssist()
                                }
                            }else if (todayAss != null) {
                                if(
                                    currentIndex == todayAss.size -1
                                ){
                                    moveToNextAssist()
                                }
                            }

                        }

                    }
                    //If it's first
                    val nextTime =_nextClassStartTime.value
                    if(nextTime != null && currentIndex == -1){
                        val remaining2 = nextTime.timeInMillis - now.timeInMillis
                        if(remaining2 <= 0){
                            moveToNextAssist()
                        }
                    }
                }
                val v = _cont.value
                if(v!=null && v<=500){
                    _cont.postValue(v + 1)
                }
                else{
                    _cont.postValue(0)
                }
            }
        }, 0, 1000) // Actualizar cada segundo
    }

    fun updateAssistsData(){
        _todayAssists.postValue(repoAssists.getTodayAssists(userManager.getIdUser()))
        val todayAss = repoAssists.getTodayAssists(userManager.getIdUser()).toMutableList()

        if(todayAss != null){
            //println("Entrooo: ${todayAss[0].startTime}")
            _isFinished.postValue(false)
            _nextClassStartTime.postValue(todayAss[0].startTime)

            val nextTime =_nextClassStartTime.value

            if(nextTime != null){
                val remaining = nextTime.timeInMillis - Calendar.getInstance().timeInMillis
                if(remaining <= 0 && _isFinished.value !=true){
                    currentIndex = 0

                    //In next reps refresh past states
                    todayAss[currentIndex].state = 4 //current, not initialized
                    _currentClassEndTime.postValue(todayAss[currentIndex].endTime)
                    if(currentIndex + 1 < todayAss.size){
                        _nextClassStartTime.postValue(todayAss[currentIndex + 1].startTime)
                    }

                    _todayAssists.postValue(todayAss)

                }


            }
        }
    }

    fun moveToNextAssist(){
        val todayAss = _todayAssists.value?.toMutableList()
        if(todayAss != null && (currentIndex + 1) < todayAss.size){
            if(currentIndex != -1){
                if(todayAss[currentIndex].state == 3 || todayAss[currentIndex].state == 4){
                    todayAss[currentIndex].state = 2 //absent
                }
            }
            currentIndex += 1
            todayAss[currentIndex].state = 4 //current, not initialized
            _currentClassEndTime.postValue(todayAss[currentIndex].endTime)
            if(currentIndex + 1 < todayAss.size){
                _nextClassStartTime.postValue(todayAss[currentIndex + 1].startTime)
            }else{
                _nextClassStartTime.postValue(null)
            }
            _todayAssists.postValue(todayAss)
        }else{
            _isFinished.postValue(true)
            _currentClassEndTime.postValue(null)
            _nextClassStartTime.postValue(null)
            currentIndex = -1
        }
    }

    fun getTimeRange(start:Calendar, end:Calendar):String{
        val mng = _dateManager.value
        return if(mng != null)
            "${mng.getHourString(start)} - ${mng.getHourString(end)}"
        else
            ""
    }

    fun takeAttendance(){
        val todayAss = _todayAssists.value?.toMutableList()
        if(todayAss != null && currentIndex != -1){
            todayAss[currentIndex].state = 1 //presenr
            _todayAssists.postValue(todayAss)
        }


    }




}