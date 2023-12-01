package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pruebita.mydailyfisiapp.data.model.domain.DailyCourseAssist
import com.pruebita.mydailyfisiapp.data.model.domain.StudentAssistUnit
import com.pruebita.mydailyfisiapp.data.model.helpers.TokenManager
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.interfaces.ApiService
import com.pruebita.mydailyfisiapp.data.repository.repositories.AttendanceRepositoryImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.CourseRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class AttendanceListTeacherViewModel
@Inject constructor(private val context: Context,private val apiService: ApiService): ViewModel(){
    private val repoCourse: CourseRepositoryImpl = CourseRepositoryImpl(apiService)
    private val repoAssist: AttendanceRepositoryImpl = AttendanceRepositoryImpl()

    private val _idSubPart = MutableLiveData<Int>(null)
    val idSubPart: LiveData<Int> = _idSubPart

    private val _idCourse = MutableLiveData<Int>(null)
    val idCourse: LiveData<Int> = _idCourse

    private val userManager: UserManager = UserManager(context)
    private val tokenManager: TokenManager = TokenManager(context)

    private val _courseName = MutableLiveData<String>("")
    val courseName: LiveData<String> = _courseName

    private val _section = MutableLiveData<Int>(0)
    val section: LiveData<Int> = _section

    private val _subPart = MutableLiveData<String>("")
    val subPart: LiveData<String> = _subPart

    private val _listStudents = MutableLiveData<MutableList<StudentAssistUnit>>(mutableListOf())
    val listStudents: LiveData<MutableList<StudentAssistUnit>> = _listStudents

    private val _listAssists = MutableLiveData<MutableList<Boolean>>(mutableListOf())
    val listAssists: LiveData<MutableList<Boolean>> = _listAssists

    private val _cantAssisted = MutableLiveData<Int>(0)
    val cantAssisted: LiveData<Int> = _cantAssisted

    private val _timeRemaining = MutableLiveData<String>("00:00:00")
    val timeRemaining: LiveData<String> = _timeRemaining

    private val _endTime = MutableLiveData<Calendar>()
    val endTime: LiveData<Calendar> = _endTime //10 minutes before! API do it!

    private val _currentTime = MutableLiveData<Calendar>()
    val currentTime: LiveData<Calendar> = _currentTime

    private val _isFinished = MutableLiveData<Boolean>(true)
    val isFinished: LiveData<Boolean> = _isFinished

    val timeZone: TimeZone = TimeZone.getTimeZone("America/Lima")

    init {

        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val calendar = Calendar.getInstance(timeZone)
                _currentTime.postValue(calendar)
                updateTimer()
                if(_isFinished.value != true){
                    val idCourse =_idCourse.value
                    val idSubPart =_idSubPart.value
                    if (idCourse != null && idSubPart != null){
                        val list = repoAssist.getAttendanceList(idCourse,idSubPart)
                        if(list != null && list.isNotEmpty()){
                            _listAssists.postValue(list)
                            _cantAssisted.postValue(list.count { it })

                        }else{
                            _listAssists.postValue(null)
                        }
                    }
                }
            }
        }, 0, 1000)

    }

    fun updateTimer(){
        val endTime = _endTime.value
        val actualTime = _currentTime.value

        if(actualTime != null && endTime != null){
            val remaining = endTime.timeInMillis - actualTime.timeInMillis
            val seconds:Int = ((remaining / 1000) % 60).toInt()
            val minutes:Int = ((remaining / (1000 * 60)) % 60).toInt()
            val hours:Int = ((remaining / (1000 * 60 * 60)) % 24).toInt()
            if(remaining >= 0){
                _timeRemaining.postValue("$hours:${String.format("%02d", minutes)}:${String.format("%02d", seconds)}")
                //println("Hora: $hours:$minutes:$seconds")
            }
            else{
                _timeRemaining.postValue("00:00:00")
                _isFinished.postValue(true)
                endAttendance()
            }

        }

    }


    fun updateShowedAttendanceList(idCourse:Int, idSubPart:Int){
        val calendar = Calendar.getInstance(timeZone)
        _currentTime.postValue(calendar)

        _idSubPart.value = idSubPart
        _idCourse.value = idCourse
        val course = repoCourse.getSubPartSummary(tokenManager.getToken(),idCourse,idSubPart, userManager.getIdUser())
        _courseName.value = course?.courseName
        _section.value = course?.section
        _subPart.value = course?.subpart
        _isFinished.value = course?.isFinished
        _endTime.value = course?.endDate

        updateTimer()

        val list = repoAssist.getStudentList(idCourse)
        val assists = repoAssist.getAttendanceList(idCourse,idSubPart)
        if(list != null && list.isNotEmpty()){
            _listStudents.value =list
            _listAssists.value =assists
            _cantAssisted.value = assists.count { it }
        }else{
            _listStudents.value = null
            _listAssists.value = null
            _cantAssisted.value = 0

        }

    }

    fun endAttendance(){
        val idCourse = _idCourse.value
        val idSubPart = _idSubPart.value
        if(idCourse !=null && idSubPart != null){
            _isFinished.postValue(true)
            repoAssist.endAttendance(idCourse, idSubPart)
        }
    }

    fun setAttendance(i:Int, state:Boolean){
        val list =_listAssists.value
        val list2 =_listStudents.value
        if(list != null && list2 != null){
            list[i] = state
            _listAssists.value = list
            val idCourse = _idCourse.value
            val idPart = _idSubPart.value
            if(idCourse != null && idPart != null){
                repoAssist.setAttendanceUser(list2[i].idStudent,idCourse,idPart,state)
            }

        }

    }
}