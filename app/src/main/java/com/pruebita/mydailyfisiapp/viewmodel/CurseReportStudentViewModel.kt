package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pruebita.mydailyfisiapp.data.model.domain.DailyCourseAssist
import com.pruebita.mydailyfisiapp.data.model.helpers.DateManager
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
class CurseReportStudentViewModel
@Inject constructor(private val context: Context,private val apiService: ApiService): ViewModel()
{
    private val repoCourse: CourseRepositoryImpl = CourseRepositoryImpl(apiService)
    private val repoAssist: AttendanceRepositoryImpl = AttendanceRepositoryImpl()
    private val _idCourse = MutableLiveData<Int>(null)
    private val userManager: UserManager = UserManager(context)
    private val tokenManager: TokenManager = TokenManager(context)
    val idCourse: LiveData<Int> = _idCourse

    private val _dateManager = MutableLiveData<DateManager>(DateManager())
    val dateManager: LiveData<DateManager> = _dateManager

    private val _courseName = MutableLiveData<String>("")
    val courseName: LiveData<String> = _courseName

    private val _section = MutableLiveData<Int>(0)
    val section: LiveData<Int> = _section

    //API not give a 16-size array, it gives a current-classes size
    private val _listAssists = MutableLiveData<MutableList<DailyCourseAssist>>(mutableListOf())
    val listAssists: LiveData<MutableList<DailyCourseAssist>> = _listAssists

    private val _totalAssistsClasses = MutableLiveData<Int>(0)
    val totalAssistsClasses: LiveData<Int> = _totalAssistsClasses

    private val _totalClasses = MutableLiveData<Int>(0)
    val totalClasses: LiveData<Int> = _totalClasses

    private val _currentAssist = MutableLiveData<DailyCourseAssist>()
    val currentAssist: LiveData<DailyCourseAssist> = _currentAssist





    init {

        val timeZone = TimeZone.getTimeZone("America/Lima")
        val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        dateFormat.timeZone = timeZone
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val idCourse =_idCourse.value
                if (idCourse != null){
                    val assists = repoAssist.getUserCourseAttendance(userManager.getIdUser(),idCourse)
                    if(assists != null && assists.isNotEmpty()){
                        _listAssists.postValue(assists)
                        _totalClasses.postValue(assists.size * 2) // Change for only theory part
                        _totalAssistsClasses.postValue(assists.count { it.theoryAssist == true } + assists.count { it.labAssist == true })

                    }
                }

            }
        }, 0, 3000)
    }


    fun updateShowedCourse(idCourse:Int){

        _idCourse.value = idCourse
        val course = repoCourse.getCourseShortInfo(tokenManager.getToken(),idCourse, userManager.getIdUser())
        _courseName.value = course?.courseName
        _section.value = course?.section

        val assists = repoAssist.getUserCourseAttendance(userManager.getIdUser(),idCourse)
        if(assists != null && assists.isNotEmpty()){
            _listAssists.value = assists
            _totalClasses.value = assists.size * 2 // Change for only theory part
            _totalAssistsClasses.value = assists.count { it.theoryAssist == true } + assists.count { it.labAssist == true }

        }else{
            _listAssists.value = null
            _totalClasses.value = 0
            _totalAssistsClasses.value = 0
            _currentAssist.value = null
        }


    }







}